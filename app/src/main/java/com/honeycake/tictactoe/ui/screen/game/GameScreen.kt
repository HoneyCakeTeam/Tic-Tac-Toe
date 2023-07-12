package com.honeycake.tictactoe.ui.screen.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.composable.IconBack
import com.honeycake.tictactoe.ui.theme.Typography

/**
 * Created by Aziza Helmy on 7/12/2023.
 */
@Composable
fun GameScreen() {
    GameContent(GameUiState())
}

@Composable
fun GameContent(state: GameUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.background), contentScale = ContentScale.Crop)
    ) {
        IconBack(
            painter = painterResource(id = R.drawable.arrow_left),
            modifier = Modifier.padding(16.dp)
        ) {}
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 64.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlayerDetails(
                name = state.firstPlayerUiState.firstPlayerName,
                image = painterResource(
                    id = state.firstPlayerUiState.firstPlayerImage
                ),
                role = painterResource(id = state.firstPlayerUiState.firstPlayerRole),
                isSelected = true
            )
            Text(text = stringResource(R.string.vs), style = Typography.displayMedium)

            PlayerDetails(
                name = state.secondPlayerUiState.secondPlayerName,
                image = painterResource(
                    id = state.secondPlayerUiState.secondPlayerImage
                ),
                role = painterResource(id = state.secondPlayerUiState.secondPlayerRole)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(16.dp)
                .border(
                    width = 1.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_game_structure),
                contentDescription = "image structure"
            )

        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewGameScreen() {
    GameScreen()
}