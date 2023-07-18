package com.honeycake.tictactoe.data

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class GameSession(
    val firstPlayerName: String = "",
    val secondPlayerName: String = "",
    val isGameCompleted: Boolean = false,
    val gameId: String = "",
){
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "firstPlayerName" to firstPlayerName,
            "secondPlayerName" to secondPlayerName,
            "isGameCompleted" to isGameCompleted,
            "gameId" to gameId,
        )
    }
}