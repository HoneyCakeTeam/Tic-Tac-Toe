package com.honeycake.tictactoe.ui.screen.game

import com.honeycake.tictactoe.R

/**
 * Created by Aziza Helmy on 7/12/2023.
 */
data class GameUiState(
    val firstPlayerUiState: PlayerUiState = PlayerUiState(),
    val secondPlayerUiState: PlayerUiState = PlayerUiState(),
    val gameState: List<ButtonState> = List(9) { ButtonState(null, true) }
)


data class PlayerUiState(
    val playerName: String = "Aziza",
    val playerRole: Int = R.drawable.x_icon,
    val playerImage: Int = R.drawable.gamer,
)

data class ButtonState(var image: Int? = null, var enabled: Boolean = true)