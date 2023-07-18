package com.honeycake.tictactoe.ui.screen.load_game


import androidx.compose.ui.platform.ClipboardManager

interface LoadScreenInteractionListener {
    fun onClickCopyIcon(localClipboardManager: ClipboardManager)
}