package com.honeycake.tictactoe.ui.screen.game

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.honeycake.tictactoe.ui.navigation.Screen

private val ROUTE = Screen.Game.route
fun NavController.navigateToGame() {
    navigate(ROUTE)
}

fun NavGraphBuilder.gameRoute() {
    composable(ROUTE) { GameScreen() }
}