package com.honeycake.tictactoe.ui.screen.create_game

/**
 * Created by Aziza Helmy on 7/15/2023.
 */
data class GameSession(
    val firstPlayerName: String = "",
    val secondPlayerName: String = "",
    val isGameCompleted: Boolean = false,
    val gameId: String = "",
)
