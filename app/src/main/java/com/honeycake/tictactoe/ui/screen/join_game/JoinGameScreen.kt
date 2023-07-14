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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.composable.ButtonItem
import com.honeycake.tictactoe.ui.composable.EditTextFile
import com.honeycake.tictactoe.ui.composable.GameTitle
import com.honeycake.tictactoe.ui.composable.GameBackground
import com.honeycake.tictactoe.ui.composable.spacing.padding_vertical.SpacerVertical16
import com.honeycake.tictactoe.ui.composable.spacing.padding_vertical.SpacerVertical52

@Composable
fun JoinGameScreen() {

    // This will be replace with stat of view model
    var name by remember { mutableStateOf("") }
    var gameId by remember { mutableStateOf("") }
    JoinGameContent(
        name = name,
        onNameChange = { name },
        gameId = gameId,
        onGameIdChange = { gameId },
    )
}

@Composable
fun JoinGameContent(
    name: String,
    onNameChange: (String) -> Unit,
    gameId: String,
    onGameIdChange: (String) -> Unit,
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                EditTextFile(
                    text = name,
                    hint = stringResource(R.string.enter_your_name),
                    placeHolder = "Ex: John",
                    onChange = onNameChange
                )
                SpacerVertical16()
                EditTextFile(
                    text = gameId,
                    hint = stringResource(R.string.your_game_id),
                    placeHolder = "Ex: fcj54nd",
                    onChange = onGameIdChange
                )
                SpacerVertical52()
                ButtonItem(text = stringResource(R.string.join_game))
            }

        }
    }
}

@Preview
@Composable
fun PreviewJoinGame() {
    JoinGameScreen()
}
