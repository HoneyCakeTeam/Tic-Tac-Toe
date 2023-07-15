package com.honeycake.tictactoe.ui.screen.create_game

import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

/**
 * Created by Aziza Helmy on 7/15/2023.
 */
@HiltViewModel
class CreateGameViewModel @Inject constructor() : ViewModel(), CreateGameInteractionListeners {

    private val _state = MutableStateFlow(CreateGameUiState())
    val state = _state.asStateFlow()

    init {
        onCreateGameClicked("Aziza")
    }

    fun onChangePlayerName(newValue: String) {
        _state.update { it.copy(firstPlayerName = newValue, isButtonEnabled = true) }
    }

    fun onCreateGameClicked(firstPlayerName: String) {
        val gameSession = GameSession(firstPlayerName, "", false, generateUniqueKey())
        FirebaseDatabase.getInstance().getReference("GameSession").push().setValue(gameSession)
    }

    private fun generateUniqueKey(): String {
        val dateFormat = SimpleDateFormat("MMddHHmmss", Locale.getDefault())
        val timestamp = dateFormat.format(Date())
        val randomNumber = (0..99999).random().toString().padStart(5, '0')

        return "$timestamp$randomNumber"
    }


}