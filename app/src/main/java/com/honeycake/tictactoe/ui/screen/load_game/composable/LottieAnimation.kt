package com.honeycake.tictactoe.ui.screen.load_game.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.honeycake.tictactoe.R

@Composable
fun LottieAnimation() {
    val animationSpeed by remember { mutableFloatStateOf(1f) }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        speed = animationSpeed,
        restartOnPlay = false
    )

    Column(
        Modifier.size(180.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        com.airbnb.lottie.compose.LottieAnimation(
           composition = composition,
            progress = progress,
            modifier = Modifier.fillMaxSize()
        )
    }
}