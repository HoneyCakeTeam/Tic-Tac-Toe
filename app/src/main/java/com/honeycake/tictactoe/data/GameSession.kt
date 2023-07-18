package com.honeycake.tictactoe.data

data class GameSession(
    val firstPlayerName: String = "",
    val secondPlayerName: String = "",
    val isGameCompleted: Boolean = false,
    val gameId: String = "",
)
