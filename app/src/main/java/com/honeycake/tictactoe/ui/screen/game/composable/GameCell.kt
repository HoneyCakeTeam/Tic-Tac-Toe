package com.honeycake.tictactoe.ui.screen.game.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun GameCell(
    modifier: Modifier = Modifier,
    imageResource: Int?,
    isEnabled: Boolean = true,
    onButtonClicked: () -> Unit,
) {
    Box(
        modifier = modifier
            .width(104.dp)
            .height(106.dp)
            .background(color = Color.Transparent)
    ) {
        Button(
            modifier = modifier.fillMaxSize(),
            shape = RoundedCornerShape(0.dp),
            onClick = onButtonClicked,
            enabled = isEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
        ) {
            if (imageResource != null) {
                Image(painter = painterResource(id = imageResource), contentDescription = "")
            }
        }
    }
}