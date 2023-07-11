package com.honeycake.tictactoe.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.composable.ButtonItem
import com.honeycake.tictactoe.ui.composable.GameTitle
import com.honeycake.tictactoe.ui.composable.GameBackground
import com.honeycake.tictactoe.ui.composable.spacing.padding_vertical.SpacerVertical16

@Composable
fun HomeScreen(
    onClickCreateButton: () -> Unit,
    onClickJoinButton: () -> Unit,
) {
    HomeContent(onClickCreateButton, onClickJoinButton)
}

@Composable
fun HomeContent(
    onClickCreateButton: () -> Unit,
    onClickJoinButton: () -> Unit,
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
                modifier = Modifier.padding(horizontal = 30.dp)
            ) {
                ButtonItem(text = stringResource(R.string.create_game), onClick = onClickCreateButton)
                SpacerVertical16()
                ButtonItem(text = stringResource(R.string.join_game), onClick = onClickJoinButton)
            }
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    HomeScreen({},{})
}
