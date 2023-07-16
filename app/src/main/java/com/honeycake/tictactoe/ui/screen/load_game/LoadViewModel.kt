package com.honeycake.tictactoe.ui.screen.load_game

import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.SavedStateHandle
import com.honeycake.tictactoe.repository.XORepository
import com.honeycake.tictactoe.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoadViewModel @Inject constructor(
    private val XORepository: XORepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<LoadUiState>(LoadUiState()), LoadScreenInteractionListener {

    val args = LoadArgs(savedStateHandle)
    override fun onClickCopyIcon(localClipboardManager: ClipboardManager) {
        localClipboardManager.setText(AnnotatedString(args.gameId!!))
    }
}