package com.honeycake.tictactoe.ui.screen.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.honeycake.tictactoe.ui.navigation.Screen

private val ROUTE = Screen.Splash.route
fun NavGraphBuilder.splashRoute() {
    composable(ROUTE) { SplashScreen() }
}