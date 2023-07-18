package com.honeycake.tictactoe.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.LocalNavigationProvider
import com.honeycake.tictactoe.ui.composable.ButtonItem
import com.honeycake.tictactoe.ui.composable.TicTacToeScaffold
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
    TicTacToeScaffold {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ButtonItem(
                text = stringResource(R.string.create_game), isEnabled = true,
                onClick = onClickCreateButton, modifier = Modifier.padding(bottom = 16.dp)
            )
            ButtonItem(
                text = stringResource(R.string.join_game),
                isEnabled = true,
                onClick = onClickJoinButton
            )
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    HomeScreen()
}
