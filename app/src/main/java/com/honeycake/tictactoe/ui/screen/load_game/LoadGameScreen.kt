package com.honeycake.tictactoe.ui.screen.load_game

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.composable.EditTextFile
import com.honeycake.tictactoe.ui.composable.GameBackground
import com.honeycake.tictactoe.ui.composable.GameTitle
import com.honeycake.tictactoe.ui.theme.TextColor
import com.honeycake.tictactoe.ui.theme.Typography

@Composable
fun LoadGameScreen(
    viewModel: LoadViewModel = hiltViewModel()
) {
    val localClipboardManager = LocalClipboardManager.current
    val gameId = viewModel.args.gameId
    LoadGameContent(
        gameId = gameId!!,
        onClickCopyIcon = { viewModel.onClickCopyIcon(localClipboardManager) })
}

@Composable
private fun LoadGameContent(gameId: String, onClickCopyIcon: () -> Unit) {
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
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 12.dp),
                    text = stringResource(R.string.your_game_id),
                    style = Typography.titleMedium,
                    color = TextColor
                )
                EditTextFile(
                    text = gameId,
                    hint = "",
                    isLeadingIcon = true,
                    onClickLeadingIcon = onClickCopyIcon,
                    readOnly = true,
                    textStyle = Typography.titleSmall
                )
            }
            Image(
                modifier = Modifier.size(140.dp),
                painter = painterResource(id = R.drawable.image_loading),
                contentDescription = "loading image",
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(R.string.waiting_sentence),
                style = Typography.labelMedium,
                color = TextColor
            )

        }
    }
}
