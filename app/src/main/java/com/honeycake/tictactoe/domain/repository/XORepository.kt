package com.honeycake.tictactoe.domain.repository

import com.honeycake.tictactoe.data.GameSession
import kotlinx.coroutines.flow.Flow

interface XORepository {
    suspend fun saveGameSession(data: Any)
    suspend fun getGameId(name: String): String
    fun notifyGameSessionChanges(gameId: String): Flow<GameSession>
}