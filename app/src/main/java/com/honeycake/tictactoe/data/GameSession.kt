package com.honeycake.tictactoe.data

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.screen.game.GameUiState

data class GameSession(
    val firstPlayerName: String = "",
    val secondPlayerName: String = "",
    val isGameCompleted: Boolean = false,
    val winner: Int? = null,
    val isTurn: Boolean = false,
    val isGameLoaded: Boolean = false,
    val board: List<List<Int>> = emptyList(),
    val gameId: String = "",
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "firstPlayerName" to firstPlayerName,
            "secondPlayerName" to secondPlayerName,
              "isGameCompleted" to isGameCompleted,
            "isGameLoaded" to isGameLoaded,
            "winner" to winner,
            "isTurn" to isTurn,
            "gameId" to gameId,
            "board" to board
        )
    }
}