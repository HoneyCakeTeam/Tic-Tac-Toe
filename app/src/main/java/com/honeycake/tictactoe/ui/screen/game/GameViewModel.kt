package com.honeycake.tictactoe.ui.screen.game

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor():ViewModel() {
    private val _state = MutableStateFlow(GameUiState())
    val state =_state.asStateFlow()


    fun onButtonClick(buttonIndex: Int) {
        val currentGameState = _state.value.gameState.toMutableList()
        val currentPlayer = if (currentGameState.count { it.image == null } % 2 == 0) {
            _state.value.firstPlayerUiState
        } else {
            _state.value.secondPlayerUiState
        }

        if (currentGameState[buttonIndex].enabled) {
            currentGameState[buttonIndex].enabled = false
            currentGameState[buttonIndex].image = currentPlayer.playerRole

            _state.value = _state.value.copy(gameState = currentGameState)

            if (checkWinCondition(currentPlayer.playerRole)) {
                //if win end game and show in ui
            }
        }

}
    private fun checkWinCondition(playerRole: Int): Boolean {
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



