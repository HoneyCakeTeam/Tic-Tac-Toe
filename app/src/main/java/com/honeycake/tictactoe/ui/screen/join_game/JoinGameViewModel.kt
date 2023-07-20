package com.honeycake.tictactoe.ui.screen.join_game

import androidx.lifecycle.viewModelScope
import com.honeycake.tictactoe.data.GameSession
import com.honeycake.tictactoe.domain.repository.XORepository
import com.honeycake.tictactoe.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinGameViewModel @Inject constructor(
    private val XORepository: XORepository,
) : BaseViewModel<JoinGameUiState>(JoinGameUiState()), JoinGameInteractionListener {

    fun onChangePlayerName(name: String) {
        updateState { it.copy(secondPlayerName = name) }
    }

    fun onChangeGameId(id: String) {
        updateState { it.copy(gameId = id, isButtonEnabled = true) }
    }

    override fun onJoinGameClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (state.value.secondPlayerName.isNotEmpty() && state.value.gameId.isNotEmpty()) {
                    updateGameSession()
                    updateState { it.copy(navigate = true) }
                }
            }catch (e: Throwable){
            }
        }

    }

    private suspend fun updateGameSession() {
            XORepository.updateGameSession(
                GameSession(
                    _state.value.firstPlayerName,
                    _state.value.secondPlayerName,
                    true,
                    _state.value.gameId
                )
            )
    }
}