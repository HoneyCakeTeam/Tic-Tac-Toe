package com.honeycake.tictactoe.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = IrishGrover,
        fontWeight = FontWeight.Normal,
        fontSize = 64.sp,
        color = TextColor
    ),
    displayMedium = TextStyle(
        fontFamily = IrishGrover,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp,
        color = TextColor
    ),
    titleLarge = TextStyle(
        fontFamily = IrishGrover,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        color = TextColor
    ),
    titleMedium = TextStyle(
        fontFamily = IrishGrover,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        color = TextColor
    ),
    titleSmall = TextStyle(
        fontFamily = IrishGrover,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = TextColor
    ),
    labelMedium = TextStyle(
        fontFamily = IrishGrover,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = TextColor
    ),
    labelSmall = TextStyle(
        fontFamily = IrishGrover,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = TextColor
    )
)