package com.honeycake.tictactoe.ui.screen.create_game

import com.honeycake.tictactoe.repository.XORepository
import com.honeycake.tictactoe.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CreateGameViewModel @Inject constructor(
    private val XORepository: XORepository
) : BaseViewModel<CreateGameUiState>(CreateGameUiState()), CreateGameInteractionListeners {

    init {
        tryToExecute(
            callee = { onCreateGameClicked("Tarek") },
            onError = ::onError,
            onSuccess = ::onSuccess
        )
    }
    fun onChangePlayerName(newValue: String) {
        updateState { it.copy(firstPlayerName = newValue, isButtonEnabled = true) }
    }

    private suspend fun onCreateGameClicked(firstPlayerName: String): String {
        val gameSession = GameSession(firstPlayerName, "", false, generateUniqueKey())
        XORepository.saveGameSession(gameSession)
        return "Done"
    }

    private fun onSuccess(result: String) {
        // do later
    }

    private fun onError(throwable: Throwable) {
        // do later
    }
     fun generateUniqueKey(): String {
        val dateFormat = SimpleDateFormat("MMddHHmmss", Locale.getDefault())
        val timestamp = dateFormat.format(Date())
        val randomNumber = (0..99999).random().toString().padStart(5, '0')

        return "$timestamp$randomNumber"
    }


}