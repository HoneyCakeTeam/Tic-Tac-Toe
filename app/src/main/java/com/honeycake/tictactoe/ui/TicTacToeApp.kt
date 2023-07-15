package com.honeycake.tictactoe.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.honeycake.tictactoe.ui.navigation.TicTacToeNavGraph

@Composable
fun TicTacToeApp() {
    CompositionLocalProvider(
        LocalNavigationProvider provides rememberNavController()) {
        TicTacToeNavGraph()
    }
}