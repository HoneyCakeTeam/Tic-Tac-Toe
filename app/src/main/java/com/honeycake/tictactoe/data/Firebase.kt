package com.honeycake.tictactoe.data

import kotlinx.coroutines.flow.Flow

interface Firebase {
    suspend fun write(gameSession: GameSession)
    suspend fun update(gameSession: GameSession): Boolean
    fun getNotify(id: String):  Flow<GameSession>
    suspend fun updateBoard(gameId: String, updatedBoard: List<Int>)
    suspend fun switchPlayer(id: String, currentPlayer: Int)
}