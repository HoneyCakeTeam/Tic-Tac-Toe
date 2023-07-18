package com.honeycake.tictactoe.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirebaseImpl : Firebase {

    override suspend fun write(gameSession: GameSession) {
        game.child(gameSession.gameId).setValue(gameSession)
    }

    override suspend fun read(id: String): GameSession {
        val data =game.child(id).get().await().value as Map<*,*>?
        return GameSession(
            firstPlayerName = data?.get("firstPlayerName") as String? ?: "",
            secondPlayerName = data?.get("secondPlayerName") as String? ?: "",
            isGameCompleted = data?.get("isGameCompleted") as Boolean? ?: false,
            gameId = data?.get("gameId") as String? ?: "",
        )
    }

    override suspend fun update(gameSession: GameSession): Boolean {
            game.child(gameSession.gameId).get().addOnSuccessListener {
                val isGameBusy = it.child("isGameCompleted").getValue(Boolean::class.java) ?: false
                if (it.exists()&& !isGameBusy){
                    val firstPlayerName = it.child("firstPlayerName").getValue(String::class.java) ?: ""
                    val newGameSession = gameSession.copy(firstPlayerName = firstPlayerName)
                    val postValues = newGameSession.toMap()
                    val childUpdates = hashMapOf<String, Any>(
                        newGameSession.gameId to postValues,
                    )
                    game.updateChildren(childUpdates)
                } else{
                    //TODO throw custom exception
                }
            }.addOnFailureListener{
               throw it
            }
            return true
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