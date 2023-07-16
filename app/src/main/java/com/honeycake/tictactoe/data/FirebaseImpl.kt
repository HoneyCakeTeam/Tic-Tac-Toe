package com.honeycake.tictactoe.data

import com.google.firebase.database.FirebaseDatabase

class FirebaseImpl : Firebase {

    override suspend fun write(data: Any) {
         game.push().setValue(data)
    }

    override suspend fun read(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun update(): Boolean {
        TODO("Not yet implemented")
    }

    companion object {
        private const val PATH = "GameSession"
        private val database = FirebaseDatabase.getInstance()
        val game = database.getReference(PATH)
    }
}