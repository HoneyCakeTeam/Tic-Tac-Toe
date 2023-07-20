package com.honeycake.tictactoe.ui.screen.game

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.honeycake.tictactoe.ui.navigation.Screen

private val ROUTE = Screen.Game.route
fun NavController.navigateToGame(gameId: String, role: Int) {
    navigate("${ROUTE}/$gameId/$role")
}

fun NavGraphBuilder.gameRoute() {
    composable(
        "${ROUTE}/{${GameArgs.GAME_ID_ARGS}}/{${GameArgs.GAME_ROLE}}", arguments = listOf(
            navArgument(GameArgs.GAME_ID_ARGS) {
                type = NavType.StringType
            },
            navArgument(GameArgs.GAME_ROLE) {
                type = NavType.IntType
            },
            )
    ) { GameScreen() }
}
class GameArgs(savedStateHandle: SavedStateHandle) {
    val gameId: String? = savedStateHandle[GAME_ID_ARGS]
    val role: Int? = savedStateHandle[GAME_ROLE]

    companion object {
        const val GAME_ID_ARGS = "gameId"
        const val GAME_ROLE = "currentRole"
    }
}