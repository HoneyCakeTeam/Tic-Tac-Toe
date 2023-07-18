package com.honeycake.tictactoe.ui.screen.create_game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.LocalNavigationProvider
import com.honeycake.tictactoe.ui.composable.ButtonItem
import com.honeycake.tictactoe.ui.composable.EditTextFile
import com.honeycake.tictactoe.ui.composable.GameBackground
import com.honeycake.tictactoe.ui.composable.GameTitle

@Composable
fun CreateGameScreen(
    viewModel: CreateGameViewModel = hiltViewModel(),
) {
    val navController = LocalNavigationProvider.current
    val state by viewModel.state.collectAsState()
    CreateGameContent(
        state = state,
        onChangePlayerName = viewModel::onChangePlayerName,
        onClickCreateGame = { viewModel.onCreateGameClicked(navController) }
    )
}

@Composable
fun CreateGameContent(
    state: CreateGameUiState,
    onChangePlayerName: (String) -> Unit,
    onClickCreateGame: () -> Unit
) {
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
                text = state.firstPlayerName,
                hint = stringResource(R.string.enter_your_name),
                placeHolder = "Ex: John",
                onChange = onChangePlayerName
            )
            ButtonItem(
                text = stringResource(R.string.create_game),
                isEnabled = state.isButtonEnabled,
                onClick = onClickCreateGame
            )
        }
    }
}