package com.honeycake.tictactoe.ui.screen.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.theme.TextColor
import com.honeycake.tictactoe.ui.theme.Typography

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background),
            contentDescription = "game splash",
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.game_logo_text),
            style = Typography.displayMedium,
            color = TextColor
        )
    }
}

@Preview
@Composable
fun Preview(){
    SplashScreen()
}