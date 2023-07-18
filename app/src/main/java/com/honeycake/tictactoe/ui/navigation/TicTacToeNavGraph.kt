package com.honeycake.tictactoe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.honeycake.tictactoe.ui.LocalNavigationProvider
import com.honeycake.tictactoe.ui.screen.create_game.createRoute
import com.honeycake.tictactoe.ui.screen.game.gameRoute
import com.honeycake.tictactoe.ui.screen.home.homeRoute
import com.honeycake.tictactoe.ui.screen.join_game.joinRoute
import com.honeycake.tictactoe.ui.screen.load_game.loadRoute

@Composable
fun TicTacToeNavGraph() {

    val navController = LocalNavigationProvider.current

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        homeRoute()
        createRoute()
        gameRoute()
        joinRoute()
        loadRoute()
    }
}