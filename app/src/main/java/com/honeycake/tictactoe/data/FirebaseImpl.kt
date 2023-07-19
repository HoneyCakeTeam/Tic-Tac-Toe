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
        val gameSessionRef = game.child(id)
        val gameSessionSnapshot = gameSessionRef.get().await()

        val initialData = gameSessionSnapshot.value as Map<*, *>?
        var gameSession = GameSession(
            firstPlayerName = initialData?.get("firstPlayerName") as String? ?: "",
            secondPlayerName = initialData?.get("secondPlayerName") as String? ?: "",
            isGameCompleted = initialData?.get("isGameCompleted") as Boolean? ?: false,
            gameId = initialData?.get("gameId") as String? ?: ""
        )

        gameSessionRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val updatedData = dataSnapshot.value as Map<*, *>?
                val updatedGameSession = gameSession.copy(
                    firstPlayerName = updatedData?.get("firstPlayerName") as String? ?: gameSession.firstPlayerName,
                    secondPlayerName = updatedData?.get("secondPlayerName") as String? ?: gameSession.secondPlayerName,
                    isGameCompleted = updatedData?.get("isGameCompleted") as Boolean? ?: gameSession.isGameCompleted,
                    gameId = updatedData?.get("gameId") as String? ?: gameSession.gameId
                )

                // Update the gameSession object with the updated data
                gameSession = updatedGameSession
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors
            }
        })

        return gameSession
    }


    override suspend fun readGameSession(gameId: String): GameSession {
        val snapshot = game.child(gameId).get().await()
        return snapshot.getValue(GameSession::class.java) ?: GameSession()
    }
    override suspend fun updateBoard(gameId: String, updatedBoard: List<List<Int>>) {
        val boardRef = game.child(gameId).child("gameState")
        boardRef.setValue(updatedBoard).await()
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