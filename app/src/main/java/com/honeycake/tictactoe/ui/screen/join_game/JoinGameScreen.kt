package com.honeycake.tictactoe.ui.screen.join_game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.LocalNavigationProvider
import com.honeycake.tictactoe.ui.composable.ButtonItem
import com.honeycake.tictactoe.ui.composable.EditTextFiled
import com.honeycake.tictactoe.ui.composable.TicTacToeScaffold
import com.honeycake.tictactoe.ui.screen.load_game.navigateToLoad

@Composable
fun JoinGameScreen() {

    // This will be replace with stat of view model
    val name by remember { mutableStateOf("") }
    val gameId by remember { mutableStateOf("") }
    val navController = LocalNavigationProvider.current
    JoinGameContent(
        name = name,
        onNameChange = { name },
        gameId = gameId,
        onGameIdChange = { gameId },
        onClickJoinGame = { navController.navigateToLoad("") }
    )
}

@Composable
private fun JoinGameContent(
    name: String,
    onNameChange: (String) -> Unit,
    gameId: String,
    onGameIdChange: (String) -> Unit,
    onClickJoinGame: () -> Unit
) {
    val navController = LocalNavigationProvider.current
    TicTacToeScaffold {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            EditTextFiled(
                text = name,
                hint = stringResource(R.string.enter_your_name),
                placeHolder = "Ex: John",
                onChange = onNameChange,
            )
            EditTextFiled(
                text = gameId,
                hint = stringResource(R.string.your_game_id),
                placeHolder = "Ex: fcj54nd",
                onChange = onGameIdChange,
            )
        }
        ButtonItem(
            text = stringResource(R.string.join_game),
            isEnabled = true,
            onClick = onClickJoinGame
        )
    }
}