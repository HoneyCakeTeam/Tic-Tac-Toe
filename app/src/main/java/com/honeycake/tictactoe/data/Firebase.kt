package com.honeycake.tictactoe.data

interface Firebase {

    suspend fun write(data: Any)
    suspend fun read(id: String): String
    suspend fun update(): Boolean

}