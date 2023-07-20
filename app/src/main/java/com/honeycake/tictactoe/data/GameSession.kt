package com.honeycake.tictactoe.data

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class GameSession(
    val firstPlayerName: String = "",
    val secondPlayerName: String = "",
    val isGameReady: Boolean = false,
    val gameId: String = "",
    val currentPlayer: Int = 1,
    val board: List<Int> = List(9) {0},
    val winner: Boolean = false,
    ){
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "firstPlayerName" to firstPlayerName,
            "secondPlayerName" to secondPlayerName,
            "isGameReady" to isGameReady,
            "gameId" to gameId,
            "currentPlayer" to currentPlayer,
            "board" to board,
            "winner" to winner
        )
    }
}