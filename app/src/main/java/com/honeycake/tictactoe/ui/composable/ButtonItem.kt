package com.honeycake.tictactoe.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.honeycake.tictactoe.ui.theme.Card
import com.honeycake.tictactoe.ui.theme.Primary38
import com.honeycake.tictactoe.ui.theme.RoundedShape
import com.honeycake.tictactoe.ui.theme.Typography

@Composable
fun ButtonItem(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean=true,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .size(width = 300.dp, height = 56.dp)
            .clip(RoundedShape.large)
            .border(1.dp, Card, RoundedShape.large)
            .background(Primary38),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Text(text = text, style = Typography.labelMedium)
    }
}