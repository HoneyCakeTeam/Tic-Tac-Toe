package com.honeycake.tictactoe.domain.repository

import com.honeycake.tictactoe.data.GameSession
import kotlinx.coroutines.flow.Flow

interface XORepository {
    suspend fun saveGameSession(gameSession: GameSession)
    suspend fun getGameId(name: String): String
    fun notifyGameSessionChanges(gameId: String): Flow<GameSession>
    suspend fun updateGameSession(gameSession: GameSession) : Boolean
}