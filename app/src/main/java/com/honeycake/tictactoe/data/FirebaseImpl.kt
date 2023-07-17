package com.honeycake.tictactoe.data


import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow



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

    override fun getNotify(id: String): Flow<GameSession> = callbackFlow {
        val listener = game.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val gameSession = dataSnapshot.children
                    .mapNotNull { it.getValue(GameSession::class.java) }
                    .first { it.gameId == id }

                trySend(gameSession).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                // handle error
            }
        })

        awaitClose { game.removeEventListener(listener) }
    }

    companion object {
        private const val PATH = "GameSession"
        private val database = FirebaseDatabase.getInstance()
        val game = database.getReference(PATH)
    }
}