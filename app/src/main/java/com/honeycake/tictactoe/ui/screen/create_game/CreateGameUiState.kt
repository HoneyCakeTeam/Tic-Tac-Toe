package com.honeycake.tictactoe.ui.screen.create_game

data class CreateGameUiState(
    val firstPlayerName: String = "",
    val isButtonEnabled: Boolean = false,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val gameId: String = "",
)