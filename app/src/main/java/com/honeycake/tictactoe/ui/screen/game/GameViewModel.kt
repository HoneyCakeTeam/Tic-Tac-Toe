package com.honeycake.tictactoe.ui.screen.game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.honeycake.tictactoe.domain.repository.XORepository
import com.honeycake.tictactoe.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val XORepository: XORepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<GameUiState>(GameUiState()) {

    private val args = GameArgs(savedStateHandle)

    init {
        updateState { it.copy(myTurn = args.role!!) }
        viewModelScope.launch {
            loadData(args.gameId!!)
        }
    }

    private fun loadData(gameId: String) {
        viewModelScope.launch {
            XORepository.loadData(gameId).collect { gameSession ->
                if (gameSession.firstPlayerName.isNotEmpty() || gameSession.secondPlayerName.isNotEmpty()) {
                    updateState {
                        it.copy(
                            firstPlayerName = gameSession.firstPlayerName,
                            secondPlayerName = gameSession.secondPlayerName,
                            currentPlayer = gameSession.currentPlayer,
                            board = gameSession.board,
                            enabled = gameSession.currentPlayer == args.role
                        )
                    }
                }
                playerTurn()
                checkGameState()
            }
        }
    }

    private fun playerTurn() {
        when (state.value.currentPlayer) {
            1 -> {
                updateState {
                    it.copy(
                        isFirstPlayerSelected = true,
                        isSecondPlayerSelected = false,
                    )
                }
            }

            2 -> {
                updateState {
                    it.copy(
                        isSecondPlayerSelected = true,
                        isFirstPlayerSelected = false,
                    )
                }
            }
        }
    }

    private fun switchPlayer() {
        if (state.value.currentPlayer == 1) {
            viewModelScope.launch {
                XORepository.switchPlayer(gameId = args.gameId!!, currentPlayer = 2)
            }
        } else
            viewModelScope.launch {
                XORepository.switchPlayer(gameId = args.gameId!!, currentPlayer = 1)
            }
    }


    private fun updateBoard(buttonIndex: Int) {
        val currentGameState = state.value.board.toMutableList()
        val currentValue = currentGameState[buttonIndex]

        if (currentValue == 0) {
            currentGameState[buttonIndex] = state.value.currentPlayer
            updateState {
                it.copy(
                    board = currentGameState.toList(),
                    enabled = state.value.currentPlayer == args.role
                )
            }
            viewModelScope.launch {
                XORepository.updateBoard(args.gameId!!, currentGameState)
            }
        }
    }

    fun onButtonClick(buttonIndex: Int) {
        updateBoard(buttonIndex)
        switchPlayer()
    }

    private fun checkGameState(){
        if (checkWin(state.value.currentPlayer)) {
            if (state.value.currentPlayer == state.value.myTurn){
                updateState { it.copy(gameResult = GameResult.WIN) }
            }else{
                updateState { it.copy(gameResult = GameResult.LOSE) }
            }
        } else if (isGameTied()) {
            updateState { it.copy( gameResult = GameResult.TIED ) }
        }
    }

    private fun isGameTied(): Boolean {
        return state.value.board.all { it != 0 }
    }

    private fun checkWin(playerRole: Int): Boolean {
        val gameState = _state.value.board
        val horizontalLines = _state.value.horizontalLines
        val verticalLines = _state.value.verticalLines
        val diagonalLines = _state.value.diagonalLines

        for (line in horizontalLines + verticalLines + diagonalLines) {
            if (line.all { gameState[it] == playerRole }) {
                _state.value = _state.value.copy(winningLine = line)
                return true
            }
        }
        return false
    }
}