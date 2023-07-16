package com.honeycake.tictactoe.ui.screen.game.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honeycake.tictactoe.R

/**
 * Created by Aziza Helmy on 7/14/2023.
 */
@Composable
fun GameCell(
    modifier: Modifier = Modifier,
    painter: Painter,
    isEnabled: Boolean = true,
    onButtonClicked: () -> Unit,
) {

    Button(
        modifier = modifier,
        shape = RoundedCornerShape(0.dp),
        onClick = onButtonClicked,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
    ) {
        Image(painter = painter, contentDescription = "")
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewGameCell() {
    GameCell(painter = painterResource(id = R.drawable.x_icon), onButtonClicked = {})
}