package com.honeycake.tictactoe.ui.navigation

sealed class Screen(val route: String) {

    object Splash : Screen("splash")
    object Home : Screen("home")
    object CreateGame : Screen("create_game")
    object JoinGame : Screen("join_game")
    object LoadGame : Screen("load_game")
    object Game : Screen("game")
}
