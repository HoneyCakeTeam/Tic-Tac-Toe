package com.honeycake.tictactoe.domain.repository

import com.honeycake.tictactoe.data.Firebase
import com.honeycake.tictactoe.data.GameSession
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class XORepositoryImpl @Inject constructor(
    private val database: Firebase
) : XORepository {
    override suspend fun saveGameSession(gameSession: GameSession) {
        database.write(gameSession)
    }

    override suspend fun getGameId(name: String): String {
        return database.read(name)
    }

    override fun notifyGameSessionChanges(gameId: String): Flow<GameSession> {
        return database.getNotify(gameId)
    }

    override suspend fun updateGameSession(gameSession: GameSession): Boolean {
        return database.update(gameSession)
    }
}