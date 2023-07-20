package com.honeycake.tictactoe.ui.screen.game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
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

    private fun loadData(gameId: String) {
        viewModelScope.launch {
            XORepository.loadData(gameId).collect { gameSession ->
                if (gameSession.firstPlayerName.isNotEmpty() || gameSession.secondPlayerName.isNotEmpty()) {
                    updateState {
                        it.copy(
                            firstPlayerName = gameSession.firstPlayerName,
                            secondPlayerName = gameSession.secondPlayerName,
                            currentPlayer = gameSession.currentPlayer,
                            board = gameSession.board,
                            enabled = gameSession.currentPlayer == args.role
                        )
                    }
                }
                playerTurn()
            }
        }
    }

    private fun playerTurn() {
        when (state.value.currentPlayer) {
            1 -> {
                updateState {
                    it.copy(
                        isFirstPlayerSelected = true,
                        isSecondPlayerSelected = false,
                    )
                }
            }

            2 -> {
                updateState {
                    it.copy(
                        isSecondPlayerSelected = true,
                        isFirstPlayerSelected = false,
                    )
                }
            }
        }
    }

    private fun switchPlayer() {
        if (state.value.currentPlayer == 1) {
            viewModelScope.launch {
                XORepository.switchPlayer(gameId = args.gameId!!, currentPlayer = 2)
            }
        } else
            viewModelScope.launch {
                XORepository.switchPlayer(gameId = args.gameId!!, currentPlayer = 1)
            }
    }




    fun updateBoard(buttonIndex: Int){
        val currentGameState = state.value.board.toMutableList()
        val currentValue = currentGameState[buttonIndex]

        if (currentValue == 0) {
            currentGameState[buttonIndex] = state.value.currentPlayer
            updateState {
                it.copy(
                    board = currentGameState.toList(),
                    enabled = state.value.currentPlayer == args.role
                )
            }
            viewModelScope.launch {
                XORepository.updateBoard(args.gameId!!, currentGameState)
            }
        }

    }

    fun onButtonClick(buttonIndex: Int) {
        updateBoard(buttonIndex)

        switchPlayer()


    }
}