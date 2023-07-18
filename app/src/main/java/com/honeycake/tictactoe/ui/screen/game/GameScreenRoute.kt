package com.honeycake.tictactoe.ui.screen.game

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.honeycake.tictactoe.ui.navigation.Screen

private val ROUTE = Screen.Game.route
fun NavController.navigateToGame(gameId: String) {
    navigate("${ROUTE}/$gameId")
}

fun NavGraphBuilder.gameRoute() {
    composable(
        "${ROUTE}/{${GameArgs.GAME_ID_ARGS}}",
        arguments = listOf(navArgument(GameArgs.GAME_ID_ARGS) { NavType.StringType })
    ) { GameScreen() }
}

class GameArgs(savedStateHandle: SavedStateHandle) {
    val gameId: String? = savedStateHandle[GAME_ID_ARGS]

    companion object {
        const val GAME_ID_ARGS = "gameId"
    }
}