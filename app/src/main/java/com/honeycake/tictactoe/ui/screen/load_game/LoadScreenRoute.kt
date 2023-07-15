package com.honeycake.tictactoe.ui.screen.load_game

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.honeycake.tictactoe.ui.navigation.Screen

private val ROUTE = Screen.LoadGame.route
fun NavController.navigateToLoad() {
    navigate(ROUTE)
}

fun NavGraphBuilder.loadRoute() {
    composable(ROUTE) { LoadGameScreen() }
}