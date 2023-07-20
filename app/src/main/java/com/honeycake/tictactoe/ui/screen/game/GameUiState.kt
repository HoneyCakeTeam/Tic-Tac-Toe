package com.honeycake.tictactoe.ui.screen.game

import com.honeycake.tictactoe.R

data class GameUiState(
    val firstPlayerName: String = "",
    val firstPlayerRole: Int = R.drawable.x_icon,
    val firstPlayerImage: Int = R.drawable.gamer,
    val secondPlayerName: String = "",
    val secondPlayerRole: Int = R.drawable.x_icon,
    val secondPlayerImage: Int = R.drawable.gammer2,
    val image: Int? = null,
    val enabled: Boolean = true,
    val isWinner: Boolean = false,
    val board: List<Int> = List(9) {0},
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
    val winningLine: List<Int>? = null,
    val isTied: Boolean = false,
    val currentPlayer: Int = 1,
    val myTurn: Int = 1,
    val isFirstPlayerSelected: Boolean = false,
    val isSecondPlayerSelected: Boolean = false,
    val gameResult: GameResult = GameResult.IDEAL
)

enum class GameResult{
    WIN, LOSE, TIED, IDEAL
}