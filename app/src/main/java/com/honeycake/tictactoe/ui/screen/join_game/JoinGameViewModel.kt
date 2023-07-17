package com.honeycake.tictactoe.ui.screen.join_game

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.honeycake.tictactoe.repository.XORepository
import com.honeycake.tictactoe.ui.base.BaseViewModel
import com.honeycake.tictactoe.ui.screen.create_game.GameSession
import com.honeycake.tictactoe.ui.screen.load_game.navigateToLoad
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinGameViewModel @Inject constructor(
    private val XORepository: XORepository,
): BaseViewModel<JoinGameUiState>(JoinGameUiState()), JoinGameInteractionListener{

    fun onChangePlayerName(name: String) {
        updateState { it.copy(secondPlayerName = name)}
    }

    fun onChangeGameId(id: String) {
        updateState { it.copy(gameId = id, isButtonEnabled = true) }
    }

      override fun onJoinGameClicked(navController: NavController) {
        if (state.value.secondPlayerName.isNotEmpty() && state.value.gameId.isNotEmpty()) {
            saveGameSession()
            navController.navigateToLoad(_state.value.gameId)
        } else
      return
    }
    
    private fun saveGameSession() {
        val gameSession = GameSession(
            "",
            _state.value.secondPlayerName,
            false,
            _state.value.gameId
        )
        viewModelScope.launch {
            XORepository.saveGameSession(gameSession)
        }
    }
}