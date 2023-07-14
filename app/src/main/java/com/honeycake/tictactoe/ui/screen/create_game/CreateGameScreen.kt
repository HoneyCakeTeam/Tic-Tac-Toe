package com.honeycake.tictactoe.ui.screen.create_game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.honeycake.tictactoe.ui.composable.GameBackground
import com.honeycake.tictactoe.ui.composable.GameTitle

@Composable
fun CreateGameScreen() {
    // This will be replace with stat of view model
    var name by remember { mutableStateOf("") }
    CreateGameContent(
        name = name,
        onNameChange = { name },
    )
}

@Composable
fun CreateGameContent(name: String, onNameChange: (String) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GameBackground()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            GameTitle()
            EditTextFile(
                text = name,
                hint = stringResource(R.string.enter_your_name),
                placeHolder = "Ex: John",
                onChange = onNameChange
            )
            ButtonItem(text = stringResource(R.string.create_game))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCreateGame() {
    CreateGameScreen()
}
