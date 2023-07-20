package com.honeycake.tictactoe.ui.screen.load_game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.LocalNavigationProvider
import com.honeycake.tictactoe.ui.composable.EditTextFiled
import com.honeycake.tictactoe.ui.composable.TicTacToeScaffold
import com.honeycake.tictactoe.ui.screen.game.navigateToGame
import com.honeycake.tictactoe.ui.screen.load_game.composable.DotsAnimation
import com.honeycake.tictactoe.ui.screen.load_game.composable.LottieAnimation
import com.honeycake.tictactoe.ui.theme.TextColor
import com.honeycake.tictactoe.ui.theme.Typography


@Composable
fun LoadGameScreen(
    viewModel: LoadViewModel = hiltViewModel()
) {
    val localClipboardManager = LocalClipboardManager.current
    val navController = LocalNavigationProvider.current
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.isSecondPlayerJoined) {
        if (state.isSecondPlayerJoined) {
            navController.navigateToGame(state.gameId, 1)
        }
    }

    LoadGameContent(
        gameId = state.gameId,
        onClickCopyIcon = { viewModel.onClickCopyIcon(localClipboardManager) })
}

@Composable
private fun LoadGameContent(gameId: String, onClickCopyIcon: () -> Unit) {
    TicTacToeScaffold {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 12.dp),
                text = stringResource(R.string.your_game_id),
                style = Typography.titleMedium,
                color = TextColor
            )
            EditTextFiled(
                text = gameId,
                hint = "",
                isLeadingIcon = true,
                onClickLeadingIcon = onClickCopyIcon,
                readOnly = true,
                textStyle = Typography.titleSmall
            )
        }
        LottieAnimation()
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Text(
                text = stringResource(R.string.waiting_sentence),
                style = Typography.labelMedium,
                color = TextColor
            )
            DotsAnimation()
        }
    }
}
