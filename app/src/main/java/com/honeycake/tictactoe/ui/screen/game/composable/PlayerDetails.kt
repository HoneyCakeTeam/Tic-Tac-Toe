package com.honeycake.tictactoe.ui.screen.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.theme.Secondary
import com.honeycake.tictactoe.ui.theme.Typography

/**
 * Created by Aziza Helmy on 7/12/2023.
 */
@Composable
fun PlayerDetails(
    image: Painter= painterResource(id = R.drawable.gammer2),
    name: String="",
    role: Painter= painterResource(id = R.drawable.x_icon),
    isSelected: Boolean = false,
    modifier: Modifier = Modifier,
) {
    Box(
        Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) Secondary else Color.Transparent)
            .padding(16.dp)
            .size(width = 91.dp, height = 115.dp)
    ) {
        Image(
            painter = image,
            contentDescription = "Player Icon",
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Fit,
            alignment = Alignment.TopCenter

        )
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = name,
                modifier = Modifier.padding(top = 24.dp),
                style = Typography.labelSmall
            )
            Icon(
                painter = role,
                contentDescription = "x icon",
                tint = if (role == painterResource(id = R.drawable.x_icon)) Color.Red else Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
