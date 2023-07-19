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
){
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "firstPlayerName" to firstPlayerName,
            "secondPlayerName" to secondPlayerName,
            "isGameReady" to isGameReady,
            "gameId" to gameId,
            "currentPlayer" to currentPlayer
        )
    }
}