package com.honeycake.tictactoe.ui.screen.join_game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.LocalNavigationProvider
import com.honeycake.tictactoe.ui.composable.ButtonItem
import com.honeycake.tictactoe.ui.composable.EditTextFiled
import com.honeycake.tictactoe.ui.composable.GameBackground
import com.honeycake.tictactoe.ui.composable.GameTitle
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
        onClickJoinGame = { navController.navigateToLoad() }
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                EditTextFiled(
                    text = name,
                    hint = stringResource(R.string.enter_your_name),
                    placeHolder = "Ex: John",
                    onChange = onNameChange,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                EditTextFiled(
                    text = gameId,
                    hint = stringResource(R.string.your_game_id),
                    placeHolder = "Ex: fcj54nd",
                    onChange = onGameIdChange,
                    modifier = Modifier.padding(bottom = 64.dp)
                )
                ButtonItem(text = stringResource(R.string.join_game), isEnabled = true,onClick = onClickJoinGame)
            }

        }
    }
}