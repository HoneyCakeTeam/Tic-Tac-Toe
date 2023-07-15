package com.honeycake.tictactoe.ui.screen.join_game

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.honeycake.tictactoe.ui.navigation.Screen

private val ROUTE = Screen.JoinGame.route
fun NavController.navigateToJoin() {
    navigate(ROUTE)
}

fun NavGraphBuilder.joinRoute() {
    composable(ROUTE) { JoinGameScreen() }
}