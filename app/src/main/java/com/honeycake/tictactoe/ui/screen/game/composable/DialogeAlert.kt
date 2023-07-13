package com.honeycake.tictactoe.ui.screen.game.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honeycake.tictactoe.R
import com.honeycake.tictactoe.ui.theme.Primary
import com.honeycake.tictactoe.ui.theme.Typography

/**
 * Created by Aziza Helmy on 7/13/2023.
 */
@Composable
fun CustomAlertDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onButtonClicked: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ), colors = CardDefaults.cardColors(containerColor = Primary),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .size(height = 100.dp, width = 150.dp)

    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)

        ) {
            Text(
                text = "Login",
                style = Typography.labelMedium
            )
            Text(
                text = "You must be login to join or create a game",
                style = Typography.labelSmall
            )
            Button(
                onClick = { onButtonClicked },
                modifier = Modifier
                // .padding(horizontal = 8.dp)
                //   , colors =

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp), horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )
                    Text(text = "Login", modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCustomAlertDialog() {
    CustomAlertDialog(onDismiss = {}, onButtonClicked = {})
}
