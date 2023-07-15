package com.honeycake.tictactoe.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.LocalNavigationProvider
import com.honeycake.tictactoe.ui.composable.ButtonItem
import com.honeycake.tictactoe.ui.composable.GameBackground
import com.honeycake.tictactoe.ui.composable.GameTitle
import com.honeycake.tictactoe.ui.screen.create_game.navigateToCreate
import com.honeycake.tictactoe.ui.screen.join_game.navigateToJoin

@Composable
fun HomeScreen() {
    val navController = LocalNavigationProvider.current
    HomeContent({ navController.navigateToCreate() }, { navController.navigateToJoin() })
}

@Composable
private fun HomeContent(
    onClickCreateButton: () -> Unit,
    onClickJoinButton: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GameBackground()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            GameTitle()
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
            ) {
                ButtonItem(text = stringResource(R.string.create_game), isEnabled = true,
                    onClick = onClickCreateButton, modifier = Modifier.padding(bottom = 16.dp))
                ButtonItem(text = stringResource(R.string.join_game), isEnabled = true,onClick = onClickJoinButton)
            }
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    HomeScreen()
}
