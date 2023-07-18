package com.honeycake.tictactoe.ui.screen.create_game

/**
 * Created by Aziza Helmy on 7/15/2023.
 */

data class CreateGameUiState(
    val firstPlayerName: String = "",
    val isButtonEnabled: Boolean = false,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val gameId: String = ""
)