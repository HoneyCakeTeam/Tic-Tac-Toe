package com.honeycake.tictactoe.data

import kotlinx.coroutines.flow.Flow

interface Firebase {

    suspend fun write(data: Any)
    suspend fun read(id: String): String
    suspend fun update(): Boolean

    fun getNotify(id: String):  Flow<GameSession>

}