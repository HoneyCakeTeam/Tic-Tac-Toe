package com.honeycake.tictactoe.ui.screen.game

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(GameUiState())
    val state = _state.asStateFlow()


    fun onButtonClick(buttonIndex: Int) {
        val currentState = _state.value
        val currentPlayer = if (currentState.gameState.count { it.image == null } % 2 == 0) {
            currentState.firstPlayerUiState
        } else {
            currentState.secondPlayerUiState
        }

        val currentGameState = currentState.gameState.toMutableList()
        val currentButtonState = currentGameState[buttonIndex]

        if (currentButtonState.enabled) {

            updateButtonState(currentButtonState, currentPlayer.playerRole)

            val updatedState = currentState.copy(gameState = currentGameState)
            _state.value = updatedState

            if (checkWin(currentPlayer.playerRole)) {
                val winner = determineWinner(currentState, currentPlayer.playerRole)
                val updatedWinner = updatePlayerAsWinner(currentState, winner)

                _state.value = updatedState.copy(
                    firstPlayerUiState = updatedWinner.firstPlayerUiState,
                    secondPlayerUiState = updatedWinner.secondPlayerUiState
                )
                Log.d("GameViewModel", "Player ${winner.playerName} wins!")
            }
        }

    }

    private fun updateButtonState(buttonState: ButtonState, playerRole: Int) {
        buttonState.enabled = false
        buttonState.image = playerRole
    }

    private fun determineWinner(currentState: GameUiState, playerRole: Int): PlayerUiState {
        return if (playerRole == currentState.firstPlayerUiState.playerRole) {
            currentState.firstPlayerUiState
        } else {
            currentState.secondPlayerUiState
        }
    }

    private fun updatePlayerAsWinner(
        currentState: GameUiState,
        winner: PlayerUiState
    ): GameUiState {
        val updatedFirstPlayer =
            currentState.firstPlayerUiState.copy(isWinner = winner == currentState.firstPlayerUiState)
        val updatedSecondPlayer =
            currentState.secondPlayerUiState.copy(isWinner = winner == currentState.secondPlayerUiState)
        return currentState.copy(
            firstPlayerUiState = updatedFirstPlayer,
            secondPlayerUiState = updatedSecondPlayer
        )
    }

    private fun checkWin(playerRole: Int): Boolean {
        val gameState = _state.value.gameState
        val horizontalLines = _state.value.horizontalLines
        val verticalLines = _state.value.verticalLines
        val diagonalLines = _state.value.diagonalLines

        for (line in horizontalLines + verticalLines + diagonalLines) {
            if (line.all { gameState[it].image == playerRole }) {
                return true
            }
        }

        return false
    }
}



