package com.honeycake.tictactoe.ui.screen.game.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.honeycake.tictactoe.ui.theme.Secondary

/**
 * Created by Aziza Helmy on 7/12/2023.
 */
@Composable
fun SelectedBackground(){
       Box(
           Modifier
               .clip(RoundedCornerShape(16.dp))
               .background(Secondary)
               .padding(16.dp)
               .size(width = 91.dp, height = 115.dp)
    ) {}
}