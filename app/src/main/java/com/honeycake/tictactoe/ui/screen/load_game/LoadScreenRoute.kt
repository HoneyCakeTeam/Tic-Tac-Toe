package com.honeycake.tictactoe.ui.screen.load_game

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.honeycake.tictactoe.ui.navigation.Screen

private val ROUTE = Screen.LoadGame.route
fun NavController.navigateToLoad(gameId: String) {
    navigate("$ROUTE/$gameId")
}

fun NavGraphBuilder.loadRoute() {
    composable(
        "$ROUTE/{${LoadArgs.GAME_ID_ARGS}}",
        arguments = listOf(navArgument(LoadArgs.GAME_ID_ARGS) { NavType.StringType })
    ) { LoadGameScreen() }
}

class LoadArgs(savedStateHandle: SavedStateHandle) {
    val gameId: String? = savedStateHandle[GAME_ID_ARGS]

    companion object {
        const val GAME_ID_ARGS = "gameId"
    }
}
