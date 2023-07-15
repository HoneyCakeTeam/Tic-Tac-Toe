package com.honeycake.tictactoe.repository

interface XORepository {
    suspend fun saveGameSession(data: Any)
    suspend fun getGameId(name: String): String
}