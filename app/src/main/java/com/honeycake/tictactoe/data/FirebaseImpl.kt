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

    override suspend fun switchPlayer(id: String , currentPlayer :Int){
       val currentPlayerRef = game.child(id).child("currentPlayer")
        currentPlayerRef.setValue(currentPlayer)
    }

    override suspend fun updateBoard(gameId: String, updatedBoard: List<Int>) {
        val boardRef = game.child(gameId).child("board")
        boardRef.setValue(updatedBoard).await()
    }
    override suspend fun update(gameSession: GameSession): Boolean {
            game.child(gameSession.gameId).get().addOnSuccessListener {
                val isGameBusy = it.child("isGameReady").getValue(Boolean::class.java) ?: false
                if (it.exists()&& !isGameBusy){
                    val firstPlayerName = it.child("firstPlayerName").getValue(String::class.java) ?: ""
                    val newGameSession = gameSession.copy(firstPlayerName = firstPlayerName)
                    val postValues = newGameSession.toMap()
                    val childUpdates = hashMapOf<String, Any>(
                        newGameSession.gameId to postValues,
                    )
                    game.updateChildren(childUpdates)
                } else{
                    throw Exception()
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