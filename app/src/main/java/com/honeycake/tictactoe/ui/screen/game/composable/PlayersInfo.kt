package com.honeycake.tictactoe.ui.screen.game.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.screen.game.GameUiState
import com.honeycake.tictactoe.ui.screen.game.PlayerDetails
import com.honeycake.tictactoe.ui.theme.Typography

/**
 * Created by Aziza Helmy on 7/14/2023.
 */

@Composable
fun PlayersInfo(state: GameUiState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 64.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlayerDetails(
            name = state.firstPlayerName,
            image = painterResource(
                id = state.firstPlayerImage
            ),
            role = painterResource(id = state.firstPlayerRole),
            isSelected = true
        )

        Text(text = stringResource(R.string.vs), style = Typography.displayMedium)

        PlayerDetails(
            name = state.secondPlayerName,
            image = painterResource(
                id = state.secondPlayerImage
            ),
            role = painterResource(id = state.secondPlayerRole),
            isSelected = true
        )
    }
}


