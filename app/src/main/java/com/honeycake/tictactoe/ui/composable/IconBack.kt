package com.honeycake.tictactoe.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.theme.Card
import com.honeycake.tictactoe.ui.theme.Secondary

/**
 * Created by Aziza Helmy on 7/12/2023.
 */
@Composable
fun IconBack(
    painter: Painter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Icon(
        painter = painter,
        contentDescription = "Back Icon ",
        modifier
            .padding(12.dp)
            .clip(CircleShape)
            .background(Secondary)
            .clickable { onClick }, tint = Card
    )
}

@Preview
@Composable
fun PreviewIconBack() {
    IconBack(painter = painterResource(id = R.drawable.arrow_left)) {

    }
}