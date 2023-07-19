package com.honeycake.tictactoe.ui.screen.game

import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.data.GameSession

/**
 * Created by Aziza Helmy on 7/12/2023.
 */
data class GameUiState(
    val firstPlayerName: String = "",
    val firstPlayerIcon: Int = R.drawable.x_icon,
    val secondPlayerName: String = "",
    val secondPlayerIcon: Int = R.drawable.o_icon,
    val isGameCompleted: Boolean = false,
    val currentPlayerName: String = "",
    val currentPlayerIcon: Int = R.drawable.x_icon,
    val winner: Int? = null,
    val isTied: Boolean = false,
    val isTurn: Boolean = true,
    val gameState: List<Int> = List(9) {0},
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
    ),
    var winningLine: List<Int>? = null
)