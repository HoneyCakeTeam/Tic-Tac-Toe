package com.honeycake.tictactoe.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.honeycake.tictactoe.TicTacToeNavGraph

@Composable
fun TicTacToeApp() {
    CompositionLocalProvider(
        LocalNavigationProvider provides rememberNavController()) {
        TicTacToeNavGraph()
    }
}

val LocalNavigationProvider = staticCompositionLocalOf<NavHostController> {
    error("No navigation host controller provided")
}