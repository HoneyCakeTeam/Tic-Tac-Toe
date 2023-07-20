package com.honeycake.tictactoe.ui.screen.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.LocalNavigationProvider
import com.honeycake.tictactoe.ui.composable.IconBack
import com.honeycake.tictactoe.ui.screen.game.composable.GameBoard
import com.honeycake.tictactoe.ui.screen.game.composable.PlayersInfo
import com.honeycake.tictactoe.ui.screen.home.navigateToHome

@Composable
fun GameScreen(gameViewModel: GameViewModel = hiltViewModel()) {
    val gameUiState by gameViewModel.state.collectAsState()
    val navController = LocalNavigationProvider.current

    GameContent(
        gameUiState,
        onButtonClicked = gameViewModel::onButtonClick,
        onClickBackButton = { navController.navigateToHome() }
    )
}

@Composable
private fun GameContent(
    gameUiState: GameUiState,
    onButtonClicked: (Int) -> Unit,
    onClickBackButton: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.background), contentScale = ContentScale.Crop)
    ) {
        IconBack(onClick = onClickBackButton)
        PlayersInfo(gameUiState)
        GameBoard(gameUiState, onButtonClicked = onButtonClicked)
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewGameScreen() {
    GameScreen()
}