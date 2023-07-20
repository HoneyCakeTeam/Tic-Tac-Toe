package com.honeycake.tictactoe.ui.screen.join_game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.LocalNavigationProvider
import com.honeycake.tictactoe.ui.composable.ButtonItem
import com.honeycake.tictactoe.ui.composable.EditTextFiled
import com.honeycake.tictactoe.ui.composable.TicTacToeScaffold
import com.honeycake.tictactoe.ui.screen.game.navigateToGame

@Composable
fun JoinGameScreen(
    viewModel: JoinGameViewModel = hiltViewModel(),
) {
    val navController = LocalNavigationProvider.current
    val state by viewModel.state.collectAsState()
    JoinGameContent(
        state = state,
        onNameChange = viewModel::onChangePlayerName,
        onGameIdChange = viewModel::onChangeGameId,
        onClickJoinGame = { viewModel.onJoinGameClicked() }
    )
    LaunchedEffect(key1 = state.navigate, block = {
        if (state.navigate) {
            navController.navigateToGame(state.gameId,2)
        }
    })
}

@Composable
private fun JoinGameContent(
    state: JoinGameUiState,
    onNameChange: (String) -> Unit,
    onGameIdChange: (String) -> Unit,
    onClickJoinGame: () -> Unit,
) {
    TicTacToeScaffold {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            Arrangement.spacedBy(16.dp)
        ) {
            EditTextFiled(
                text = state.secondPlayerName,
                hint = stringResource(R.string.enter_your_name),
                placeHolder = "Ex: John",
                onChange = onNameChange,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            EditTextFiled(
                text = state.gameId,
                hint = stringResource(R.string.your_game_id),
                placeHolder = "Ex: fcj54nd",
                onChange = onGameIdChange,
                modifier = Modifier.padding(bottom = 64.dp)
            )
            ButtonItem(
                text = stringResource(R.string.join_game),
                isEnabled = state.isButtonEnabled,
                onClick = onClickJoinGame
            )
        }
    }
}