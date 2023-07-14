package com.honeycake.tictactoe.ui.screen.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.composable.IconBack
import com.honeycake.tictactoe.ui.screen.game.composable.GameBoard
import com.honeycake.tictactoe.ui.screen.game.composable.PlayersInfo

/**
 * Created by Aziza Helmy on 7/12/2023.
 */
@Composable
fun GameScreen() {
    GameContent(GameUiState(), onButtonClicked = {})
}

@Composable
fun GameContent(gameUiState: GameUiState, onButtonClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.background), contentScale = ContentScale.Crop)
    ) {
        IconBack(
            painter = painterResource(id = R.drawable.arrow_left),
            modifier = Modifier.padding(16.dp)
        ) {}
        PlayersInfo(gameUiState)
        GameBoard(gameUiState, onButtonClicked = onButtonClicked)
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewGameScreen() {
    GameScreen()
}