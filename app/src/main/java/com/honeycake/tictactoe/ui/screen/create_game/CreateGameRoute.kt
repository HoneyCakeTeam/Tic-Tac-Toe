package com.honeycake.tictactoe.ui.screen.create_game

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.honeycake.tictactoe.ui.navigation.Screen

private val ROUTE = Screen.CreateGame.route
fun NavController.navigateToCreate() {
    navigate(ROUTE)
}

fun NavGraphBuilder.createRoute() {
    composable(ROUTE) { CreateGameScreen() }
}