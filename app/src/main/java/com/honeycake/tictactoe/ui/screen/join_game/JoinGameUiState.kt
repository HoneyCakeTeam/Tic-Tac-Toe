package com.honeycake.tictactoe.ui.screen.join_game

data class JoinGameUiState(
    val firstPlayerName: String = "",
    val secondPlayerName: String = "",
    val isGameCompleted: Boolean = false,
    val gameId: String = "",
    val isButtonEnabled: Boolean = false,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val navigate: Boolean = false,
)
