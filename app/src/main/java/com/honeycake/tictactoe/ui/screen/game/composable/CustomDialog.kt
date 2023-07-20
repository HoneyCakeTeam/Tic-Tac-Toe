package com.honeycake.tictactoe.ui.screen.game.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.screen.game.GameResult
import com.honeycake.tictactoe.ui.theme.RoundedShape
import com.honeycake.tictactoe.ui.theme.Typography
import com.honeycake.tictactoe.ui.theme.White60

@Composable
fun CustomDialog(
    openDialogCustom: MutableState<Boolean>,
    onClickButton: () -> Unit,
    gameResult: GameResult
) {
    Dialog(onDismissRequest = { openDialogCustom.value = false }) {
        CustomDialogUi(onClickButton = onClickButton, gameResult = gameResult)
    }
}

@Composable
fun CustomDialogUi(
    modifier: Modifier = Modifier,
    onClickButton: () -> Unit,
    gameResult: GameResult
) {
    Card(
        shape = RoundedShape.extraLarge,
        elevation = 8.dp
    ) {
        Column(modifier.background(com.honeycake.tictactoe.ui.theme.Dialog)) {
            Image(
                painter = when(gameResult){
                    GameResult.WIN -> painterResource(R.drawable.winning_cup)
                    GameResult.LOSE -> painterResource(R.drawable.game_over)
                    GameResult.TIED -> painterResource(R.drawable.scoreboard_tied)
                    GameResult.IDEAL -> painterResource(R.drawable.scoreboard_tied)
                },
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .height(70.dp)
                    .fillMaxWidth(),
            )
            Text(
                text = when(gameResult){
                    GameResult.WIN -> "You Won"
                    GameResult.LOSE -> "you Lose"
                    GameResult.TIED -> "The game is Tied"
                    GameResult.IDEAL -> ""
                },
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                style = Typography.titleMedium,
            )
            Text(
                        text = when(gameResult){
                            GameResult.WIN -> "Congratulation👏🏻👏🏻🎉"
                            GameResult.LOSE -> "Losseeeer😢😢"
                            GameResult.TIED -> "Bad Game😡😡😡"
                            GameResult.IDEAL -> ""
                        },
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                style = Typography.labelMedium
            )
            TextButton(
                onClick = onClickButton,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(16.dp)
                    .clip(RoundedShape.large),
                colors = ButtonDefaults.textButtonColors(White60)
            ) {
                Text(
                    text = "Back to home",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    style = Typography.labelSmall
                )
            }
        }
    }
}