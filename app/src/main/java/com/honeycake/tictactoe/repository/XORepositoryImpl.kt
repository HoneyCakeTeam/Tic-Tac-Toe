package com.honeycake.tictactoe.repository

import com.honeycake.tictactoe.data.Firebase
import javax.inject.Inject

class XORepositoryImpl @Inject constructor(
    private val database: Firebase
) : XORepository {
    override suspend fun saveGameSession(data: Any) {
        database.write(data)
    }

    override suspend fun getGameId(name: String): String {
        return database.read(name)
    }

}