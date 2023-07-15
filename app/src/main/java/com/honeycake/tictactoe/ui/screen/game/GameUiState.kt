package com.honeycake.tictactoe.ui.screen.game

import com.honeycake.tictactoe.R

/**
 * Created by Aziza Helmy on 7/12/2023.
 */
data class GameUiState(
    val firstPlayerUiState: PlayerUiState = PlayerUiState(),
    val secondPlayerUiState: PlayerUiState = PlayerUiState(),
    val gameState: List<ButtonState> = List(9) { ButtonState(null, true)},
    val horizontalLines: List<List<Int>> = listOf(
        listOf(0, 1, 2),
        listOf(3, 4, 5),
        listOf(6, 7, 8)
    ),
    val verticalLines: List<List<Int>> = listOf(
        listOf(0, 3, 6),
        listOf(1, 4, 7),
        listOf(2, 5, 8)
    ),
    val diagonalLines: List<List<Int>> = listOf(
        listOf(0, 4, 8),
        listOf(2, 4, 6)
    )
)


data class PlayerUiState(
    val playerName: String = "Aziza",
    val playerRole: Int = R.drawable.x_icon,
    val playerImage: Int = R.drawable.gamer,
    val isWinner: Boolean = false
)

data class ButtonState(var image: Int?, var enabled: Boolean = true)