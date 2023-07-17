package com.honeycake.tictactoe.ui.screen.load_game

import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.honeycake.tictactoe.domain.repository.XORepository
import com.honeycake.tictactoe.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadViewModel @Inject constructor(
    private val XORepository: XORepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<LoadUiState>(LoadUiState()), LoadScreenInteractionListener {

    private val args = LoadArgs(savedStateHandle)

    init {
        updateState { it.copy(gameId = args.gameId!!) }
        getNotifyGameSessionChanges()
    }

    private fun getNotifyGameSessionChanges() {
        viewModelScope.launch {
            XORepository.notifyGameSessionChanges(_state.value.gameId).collect { gameSession ->
                if (gameSession.secondPlayerName.isNotEmpty()) {
                    updateState { it.copy(isSecondPlayerJoined = true) }
                }
            }
        }
    }

    override fun onClickCopyIcon(localClipboardManager: ClipboardManager) {
        localClipboardManager.setText(AnnotatedString(_state.value.gameId))
    }
}