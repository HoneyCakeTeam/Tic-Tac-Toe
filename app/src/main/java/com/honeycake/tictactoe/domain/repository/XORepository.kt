package com.honeycake.tictactoe.domain.repository

import com.honeycake.tictactoe.data.GameSession
import kotlinx.coroutines.flow.Flow

interface XORepository {
    suspend fun saveGameSession(gameSession: GameSession)
    fun notifyGameSessionChanges(gameId: String): Flow<GameSession>
    suspend fun updateGameSession(gameSession: GameSession) : Boolean
    suspend fun loadData(gameId:String):Flow<GameSession>
     suspend fun switchPlayer(gameId: String,currentPlayer: Int)
    suspend fun updateBoard(gameId: String, updatedBoard: List<Int>)

}