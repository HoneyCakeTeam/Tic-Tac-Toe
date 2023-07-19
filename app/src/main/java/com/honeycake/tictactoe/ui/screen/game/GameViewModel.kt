package com.honeycake.tictactoe.ui.screen.game

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.data.GameSession
import com.honeycake.tictactoe.domain.repository.XORepository
import com.honeycake.tictactoe.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val XORepository: XORepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<GameUiState>(GameUiState()) {

    private val args = GameArgs(savedStateHandle)

    init {
        loadData(args.gameId!!)
        observeGameSession(args.gameId!!)
    }

    private fun loadData(gameId: String) {
        viewModelScope.launch {
            XORepository.notifyGameSessionChanges(gameId).collect { gameSession ->
                val isGameCompleted = gameSession.isGameCompleted
                val winner = if (isGameCompleted) getWinner(gameSession) else null

                updateState { currentState ->
                    currentState.copy(
                        firstPlayerName = gameSession.firstPlayerName,
                        secondPlayerName = gameSession.secondPlayerName,
                        isGameCompleted = isGameCompleted,
                        winner = winner,
                        winningLine = null,
                        currentPlayerName = if (currentState.isTurn) gameSession.firstPlayerName else gameSession.secondPlayerName
                    )
                }

                initializeBoard(gameSession) // Add this line to initialize the board
            }
        }
    }


    private fun observeGameSession(gameId: String) {
        viewModelScope.launch {
            XORepository.notifyGameSessionChanges(gameId).collect { gameSession ->
                updateState { currentState ->
                    currentState.copy(
                        gameState = gameSession.board
                    )
                }
            }
        }
    }

    private fun initializeBoard(gameSession: GameSession) {
        val updatedBoard = gameSession.board.map { it.toList() }
        val gameState = mutableListOf<ButtonState>()

        for (row in updatedBoard) {
            for (cellValue in row) {
                val buttonState = ButtonState(
                   // image = if (cellValue == 0) R.drawable.x_icon else if (cellValue == 1) R.drawable.o_icon else null,
                  //  enabled = cellValue == null
                )
                gameState.add(buttonState)
            }
        }

        updateState { currentState ->
            currentState.copy(
                gameState = gameState,
                isTurn = gameSession.isTurn
            )
        }
    }

    fun onButtonClick(buttonIndex: Int) {
        val currentState = _state.value
        val currentGameState = currentState.gameState.toMutableList()
        val currentButtonState = currentGameState[buttonIndex]

        if (currentButtonState.enabled && currentButtonState.image == null) {
            val currentPlayerIcon = currentState.currentPlayerIcon
            val updatedButtonState = updateButtonState(currentButtonState, currentPlayerIcon)
            currentGameState[buttonIndex] = updatedButtonState

            val updatedState = currentState.copy(gameState = currentGameState.toList())

            // Update the game board state
            val updatedBoard = updateGameBoard(currentState.board, buttonIndex, currentPlayerIcon)
            val updatedStateWithBoard = updatedState.copy(board = updatedBoard)

            _state.update { updatedStateWithBoard }
            viewModelScope.launch {
                XORepository.updateBoard(args.gameId!!, updatedBoard)
            }

            if (checkWin(updatedStateWithBoard, currentPlayerIcon)) {
                val winnerIcon = if (currentPlayerIcon == currentState.firstPlayerIcon) currentState.firstPlayerIcon else currentState.secondPlayerIcon
                val updatedWinner = updatePlayerAsWinner(updatedStateWithBoard, winnerIcon)
                _state.update { updatedWinner }
                Log.d("GameViewModel", "Player ${currentState.currentPlayerName} wins!")
            } else if (isGameTied(updatedStateWithBoard)) {
                _state.update { it.copy(isTied = true) }
                Log.d("GameViewModel", "The game is tied!")
            }

            // Switch turn
            _state.update { currentState.copy(isTurn = !currentState.isTurn) }
        } else {
            Log.d("GameViewModel", "Button $buttonIndex is disabled")
        }
    }

    private fun updateGameBoard(
        currentBoard: List<List<Int>>,
        buttonIndex: Int,
        currentPlayerIcon: Int
    ): List<List<Int>> {
        val updatedBoard = currentBoard.toMutableList()

        val row = buttonIndex / 3
        val column = buttonIndex % 3

        val rowList = updatedBoard[row].toMutableList()
        rowList[column] = if (currentPlayerIcon == R.drawable.x_icon) R.drawable.x_icon else R.drawable.o_icon

        updatedBoard[row] = rowList

        return updatedBoard
    }

    private fun updateButtonState(buttonState: ButtonState, playerIcon: Int): ButtonState {
        return buttonState.copy(image = playerIcon, enabled = false)
    }

    private fun checkWin(currentState: GameUiState, playerIcon: Int): Boolean {
        val gameState = currentState.gameState
        val linesToCheck =
            currentState.horizontalLines + currentState.verticalLines + currentState.diagonalLines

        for (line in linesToCheck) {
            val lineValues = line.map { gameState[it].image }
            if (lineValues.all { it == playerIcon }) {
                currentState.winningLine = line
                return true
            }
        }

        return false
    }

    private fun isGameTied(currentState: GameUiState): Boolean {
        return currentState.gameState.none { it.enabled && it.image == null }
    }

    private fun updatePlayerAsWinner(currentState: GameUiState, winner: Int): GameUiState {
        return currentState.copy(
            winner = winner,
            winningLine = null
        )
    }

    private fun getCurrentPlayerIcon(gameSession: GameSession): Int {
        val currentState = _state.value
        return if (gameSession.firstPlayerName == currentState.currentPlayerName) {
            currentState.firstPlayerIcon
        } else {
            currentState.secondPlayerIcon
        }
    }

    private fun getWinner(gameSession: GameSession): Int? {
        val currentState = _state.value
        return if (gameSession.winner == 1) {
            currentState.firstPlayerIcon
        } else if (gameSession.winner == 2) {
            currentState.secondPlayerIcon
        } else {
            null
        }
    }
}
