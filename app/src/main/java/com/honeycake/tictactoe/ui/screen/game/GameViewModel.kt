package com.honeycake.tictactoe.ui.screen.game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.FirebaseDatabase
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.domain.repository.XORepository
import com.honeycake.tictactoe.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val XORepository: XORepository,
    savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<GameUiState>(GameUiState()) {

    private val args = GameArgs(savedStateHandle)

    init {
        viewModelScope.launch {
            loadData(args.gameId!!)
        }
    }

    private suspend fun loadData(gameId:String){
        viewModelScope.launch{
            val gameSession = XORepository.loadData(gameId)
            getRole()
            updateState { it.copy(firstPlayerName = gameSession.firstPlayerName) }
            updateState { it.copy(secondPlayerName = gameSession.secondPlayerName) }

        }
    }

    private fun getRole() {
        val rand = (0..1).random()
        if (rand == 1) {
            updateState { it.copy(firstPlayerRole = R.drawable.x_icon) }
        } else {
            updateState { it.copy(secondPlayerRole = R.drawable.o_icon) }
        }

    }

    private val database = FirebaseDatabase.getInstance()

    private val turnRef = database.getReference("GameSession")
        .child("071906433798066").child("Turn")


//    fun initializeGameSession() {
//        turnRef.setValue(1)
//    }
//
//    fun getCurrentPlayer(callback: (Int) -> Unit) {
//        turnRef.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val currentPlayer = dataSnapshot.getValue(Int::class.java) ?: 1
//                callback(currentPlayer)
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Handle errors
//            }
//        })
//    }
//
//    fun switchPlayer() {
//        getCurrentPlayer { currentPlayer ->
//            val nextPlayer = if (currentPlayer == 1) 2 else 1
//            turnRef.setValue(nextPlayer)
//        }
//    }

    fun onButtonClick(buttonIndex: Int) {
        val currentState = _state.value

//        getCurrentPlayer { player ->
//            val currentState = _state.value
//            val currentPlayer = if (player == 1) {
//                currentState.firstPlayerUiState
//            } else {
//                currentState.secondPlayerUiState
//            }}
//        val currentPlayer = if (currentState.gameState.count { it.image == null } % 2 == 0) {
//            currentState.firstPlayerUiState
//        } else {
//            currentState.secondPlayerUiState
//        }

//        val currentGameState = currentState.gameState.toMutableList()
//        val currentButtonState = currentGameState[buttonIndex]
//
//        if (currentButtonState.enabled) {
//            val updatedButtonState = updateButtonState(currentButtonState, currentPlayer.playerRole)
//            currentGameState.removeAt(buttonIndex)
//            currentGameState.add(buttonIndex,updatedButtonState)
//
//            Log.d("GameViewModel", "currentButtonState $currentButtonState")
//
//            val updatedState = currentState.copy(gameState = currentGameState.toList())
//            _state.update { updatedState }
//            Log.d("GameViewModel", "updatedState $updatedState ")
//
//            Log.d("GameViewModel", "Button $buttonIndex clicked by ${currentPlayer.playerName}")
//
//            if (checkWin(currentPlayer.playerRole)) {
//                val winner = determineWinner(currentState, currentPlayer.playerRole)
//                val updatedWinner = updatePlayerAsWinner(currentState, winner)
//
//                _state.value = updatedState.copy(
//                    firstPlayerUiState = updatedWinner.firstPlayerUiState,
//                    secondPlayerUiState = updatedWinner.secondPlayerUiState
//                )
//
//                Log.d("GameViewModel", "Player ${winner.playerName} wins!")
//            } else if (isGameTied(updatedState)) {
//                _state.value = updatedState.copy(isTied = true)
//                Log.d("GameViewModel", "The game is tied!")
//            }
//        } else {
////            switchPlayer()
//            Log.d("GameViewModel", "Button $buttonIndex is disabled")
//        }
    }

    private fun isGameTied(currentState: GameUiState): Boolean {
        return currentState.gameState.none { it.enabled }
    }

    private fun updateButtonState(buttonState: ButtonState, playerRole: Int):ButtonState {
        return buttonState.copy(  image = playerRole ,enabled = false)
    }

//    private fun determineWinner(currentState: GameUiState, playerRole: Int): PlayerUiState {
//        return if (playerRole == currentState.firstPlayerUiState.playerRole) {
//            currentState.firstPlayerUiState
//        } else {
//            currentState.secondPlayerUiState
//        }
//    }
//
//    private fun updatePlayerAsWinner(
//        currentState: GameUiState,
//        winner: PlayerUiState
//    ): GameUiState {
//        val updatedFirstPlayer =
//            currentState.firstPlayerUiState.copy(isWinner = winner == currentState.firstPlayerUiState)
//        val updatedSecondPlayer =
//            currentState.secondPlayerUiState.copy(isWinner = winner == currentState.secondPlayerUiState)
//        return currentState.copy(
//            firstPlayerUiState = updatedFirstPlayer,
//            secondPlayerUiState = updatedSecondPlayer
//        )
//    }
//
//    private fun checkWin(playerRole: Int): Boolean {
//        val gameState = _state.value.gameState
//        val horizontalLines = _state.value.horizontalLines
//        val verticalLines = _state.value.verticalLines
//        val diagonalLines = _state.value.diagonalLines
//
//        for (line in horizontalLines + verticalLines + diagonalLines) {
//            if (line.all { gameState[it].image == playerRole }) {
//                _state.value = _state.value.copy(winningLine = line)
//                return true
//            }
//        }
//        return false
//    }

}