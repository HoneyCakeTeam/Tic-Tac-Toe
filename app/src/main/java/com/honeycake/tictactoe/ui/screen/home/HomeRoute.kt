package com.honeycake.tictactoe.ui.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.honeycake.tictactoe.ui.navigation.Screen

private val ROUTE = Screen.Home.route
fun NavController.navigateToHome() {
    navigate(ROUTE){
        this.popUpTo(ROUTE)
    }
}

fun NavGraphBuilder.homeRoute() {
    composable(ROUTE) { HomeScreen() }
}