package com.honeycake.tictactoe

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.honeycake.tictactoe.ui.LocalNavigationProvider
import com.honeycake.tictactoe.ui.screen.create_game.CreateGameScreen
import com.honeycake.tictactoe.ui.screen.load_game.LoadGameScreen
import com.honeycake.tictactoe.ui.screen.game.GameScreen
import com.honeycake.tictactoe.ui.screen.join_game.JoinGameScreen
import com.honeycake.tictactoe.ui.screen.start.HomeScreen

@Composable
fun TicTacToeNavGraph() {
    val navController = LocalNavigationProvider.current
    NavHost(navController = navController, startDestination = Screens.Home.route){
        composable(Screens.Home.route){
            HomeScreen(
                navigateToCreateGame = { navController.navigate(Screens.CreateGame.route) },
                navigateToJoinGame = { navController.navigate(Screens.JoinGame.route)}
            )
        }
        composable(Screens.CreateGame.route){
            CreateGameScreen(
                navigateToGame = { navController.navigate(Screens.Game.route) }
            )
        }
        composable(Screens.JoinGame.route){
            JoinGameScreen(
                navigateToLoadGame = { navController.navigate(Screens.LoadGame.route) }
            )
        }
        composable(Screens.LoadGame.route){
            LoadGameScreen()
        }
        composable(Screens.Game.route){
            GameScreen()
        }
    }
}




sealed class Screens(val route: String){
    object Home: Screens("home")
    object CreateGame: Screens("create_game")
    object JoinGame: Screens("join_game")
    object LoadGame: Screens("load_game")
    object Game: Screens("game")
}