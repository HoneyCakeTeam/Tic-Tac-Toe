package com.honeycake.tictactoe.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.theme.Card
import com.honeycake.tictactoe.ui.theme.Secondary

@Composable
fun IconBack(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier.padding(top = 32.dp, start = 16.dp, bottom = 16.dp),
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_left),
            contentDescription = "Back Icon",
            modifier.size(48.dp)
                .clip(CircleShape)
                .background(Secondary).padding(2.dp)
            , tint = Card
        )
    }
}
