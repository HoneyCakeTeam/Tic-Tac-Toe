package com.honeycake.tictactoe.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.honeycake.tictactoe.ui.theme.Card
import com.honeycake.tictactoe.ui.theme.RoundedShape
import com.honeycake.tictactoe.ui.theme.Typography

@Composable
fun ButtonItem(
    text: String,
    modifier: Modifier = Modifier
) {
    val horizontalGradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xff191E93),
            Color(0x191E93)
        )
    )

    Button(
        onClick = { },
        modifier = modifier
            .fillMaxWidth()
            .padding(30.dp)
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {

        Box(
            modifier = modifier.fillMaxSize().clip(RoundedShape.large)
                .background(horizontalGradientBrush).border(1.dp, Card, RoundedShape.large),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, style = Typography.labelMedium)
        }

    }
}