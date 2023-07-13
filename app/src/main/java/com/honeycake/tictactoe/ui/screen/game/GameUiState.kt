package com.honeycake.tictactoe.ui.screen.game

import com.honeycake.tictactoe.R

/**
 * Created by Aziza Helmy on 7/12/2023.
 */
data class GameUiState(
    val firstPlayerUiState: FirstPlayerUiState = FirstPlayerUiState(),
    val secondPlayerUiState: SecondPlayerUiState = SecondPlayerUiState()
)

data class FirstPlayerUiState(
    val firstPlayerName: String = "Aziza",
    val firstPlayerRole: Int = R.drawable.x_icon,
    val firstPlayerImage: Int = R.drawable.gamer,
)

data class SecondPlayerUiState(
    val secondPlayerName: String = "Menna",
    val secondPlayerRole: Int = R.drawable.o_icon,
    val secondPlayerImage: Int = R.drawable.gammer2,
)
data class ButtonState(var text: String, var enabled: Boolean)