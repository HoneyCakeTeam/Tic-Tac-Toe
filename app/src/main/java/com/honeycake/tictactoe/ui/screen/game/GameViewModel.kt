package com.honeycake.tictactoe.ui.screen.game

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.honeycake.tictactoe.R
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
                val winner = if (isGameCompleted) gameSession.winner else null

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

    fun onButtonClick(index: Int) {
        val currentState = _state.value
        val currentPlayerIcon = currentState.currentPlayerIcon
        val currentGameState = currentState.gameState.toMutableList()
        val currentValue = currentGameState[index]

        if (currentValue == 0) {
            currentGameState[index] = if (currentPlayerIcon == R.drawable.x_icon) 1 else 2

            val updatedState = currentState.copy(gameState = currentGameState.toList())

            _state.update { updatedState }
            viewModelScope.launch {
                XORepository.updateBoard(args.gameId!!, currentGameState)
            }

            if (checkWin(updatedState, currentPlayerIcon)) {
                val playerIcon =
                    if (currentPlayerIcon == currentState.firstPlayerIcon) currentState.firstPlayerIcon else currentState.secondPlayerIcon
                val updatedWinner = updatePlayerAsWinner(updatedState, playerIcon)
                _state.update { updatedWinner }
            } else if (isGameTied(updatedState)) {
                _state.update { it.copy(isTied = true) }
            }

            // Switch turn and update current player's name
            val updatedCurrentPlayerName = if (currentState.isTurn) currentState.secondPlayerName else currentState.firstPlayerName
            _state.update { currentState.copy(isTurn = !currentState.isTurn, currentPlayerName = updatedCurrentPlayerName) }
        } else {
        }
    }



    private fun checkWin(currentState: GameUiState, playerIcon: Int): Boolean {
        val gameState = currentState.gameState
        val linesToCheck =
            currentState.horizontalLines + currentState.verticalLines + currentState.diagonalLines

        for (line in linesToCheck) {
            val lineValues = line.map { gameState[it] }
            if (lineValues.all { it == playerIcon }) {
                currentState.winningLine = line
                return true
            }
        }
        return false
    }

    private fun isGameTied(currentState: GameUiState): Boolean {
        return currentState.gameState.all { it != 0 }
    }

    private fun updatePlayerAsWinner(currentState: GameUiState, winner: Int): GameUiState {
        return currentState.copy(
            winner = winner,
            winningLine = null
        )
    }
}
