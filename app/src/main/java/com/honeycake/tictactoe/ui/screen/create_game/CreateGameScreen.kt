package com.honeycake.tictactoe.ui.screen.create_game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.LocalNavigationProvider
import com.honeycake.tictactoe.ui.composable.ButtonItem
import com.honeycake.tictactoe.ui.composable.EditTextFiled
import com.honeycake.tictactoe.ui.composable.TicTacToeScaffold
import com.honeycake.tictactoe.ui.theme.Typography

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
    onClickCreateGame: () -> Unit,
) {
    TicTacToeScaffold {
        EditTextFiled(
            text = state.firstPlayerName,
            hint = stringResource(R.string.enter_your_name),
            placeHolder = "Ex: John",
            onChange = onChangePlayerName,
            textStyle = Typography.titleSmall
        )
        ButtonItem(
            text = stringResource(R.string.create_game),
            onClick = onClickCreateGame
        )
    }
}