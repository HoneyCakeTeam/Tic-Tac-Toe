package com.honeycake.tictactoe.ui.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.honeycake.tictactoe.ui.theme.TextColor
import com.honeycake.tictactoe.ui.theme.Typography

@Composable
fun GameTitle(){
    Text(
        text = "Tic Tac\nToe",
        style = Typography.displayLarge,
        color = TextColor,
        textAlign = TextAlign.Center
    )
}