package com.honeycake.tictactoe.ui.screen.game

import android.util.Log
import androidx.lifecycle.ViewModel
import com.honeycake.tictactoe.data.GameSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(GameUiState())
    val state = _state.asStateFlow()

    private val _gameState = MutableStateFlow( GameSession())

    init {
        getPlayerName()
    }
    private fun getPlayerName(){
        _state.update { it.copy(
            firstPlayerUiState =PlayerUiState(playerName = _gameState.value.firstPlayerName),
            secondPlayerUiState =PlayerUiState(playerName = _gameState.value.secondPlayerName),
        ) }
    }

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
            val updatedButtonState = updateButtonState(currentButtonState, currentPlayer.playerRole)
            currentGameState.removeAt(buttonIndex)
            currentGameState.add(buttonIndex,updatedButtonState)

            Log.d("GameViewModel", "currentButtonState $currentButtonState")

            val updatedState = currentState.copy(gameState = currentGameState.toList())
            _state.update { updatedState }
            Log.d("GameViewModel", "updatedState $updatedState ")

            Log.d("GameViewModel", "Button $buttonIndex clicked by ${currentPlayer.playerName}")

            if (checkWin(currentPlayer.playerRole)) {
                val winner = determineWinner(currentState, currentPlayer.playerRole)
                val updatedWinner = updatePlayerAsWinner(currentState, winner)

                _state.value = updatedState.copy(
                    firstPlayerUiState = updatedWinner.firstPlayerUiState,
                    secondPlayerUiState = updatedWinner.secondPlayerUiState
                )

                Log.d("GameViewModel", "Player ${winner.playerName} wins!")
            } else if (isGameTied(updatedState)) {
                _state.value = updatedState.copy(isTied = true)
                Log.d("GameViewModel", "The game is tied!")
            }
        } else {
            Log.d("GameViewModel", "Button $buttonIndex is disabled")
        }
    }

    private fun isGameTied(currentState: GameUiState): Boolean {
        return currentState.gameState.none { it.enabled }
    }

    private fun updateButtonState(buttonState: ButtonState, playerRole: Int):ButtonState {
        return buttonState.copy(  image = playerRole ,enabled = false)
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
                _state.value = _state.value.copy(winningLine = line)
                return true
            }
        }
        return false
    }

}