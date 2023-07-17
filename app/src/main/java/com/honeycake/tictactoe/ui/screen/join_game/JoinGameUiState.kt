package com.honeycake.tictactoe.ui.screen.join_game

data class JoinGameUiState(
    val secondPlayerName: String = "",
    val gameId: String = "",
    val isButtonEnabled: Boolean = false,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)
