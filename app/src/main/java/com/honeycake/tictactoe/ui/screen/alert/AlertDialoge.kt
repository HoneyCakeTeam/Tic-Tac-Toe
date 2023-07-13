package com.honeycake.tictactoe.ui.screen.alert

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import com.honeycake.tictactoe.ui.screen.game.composable.CustomAlertDialog

/**
 * Created by Aziza Helmy on 7/13/2023.
 */
@Composable
fun AlertDialog(onDismiss: () -> Unit,) {
    Dialog(onDismissRequest = onDismiss) {
        CustomAlertDialog(onDismiss = onDismiss, onButtonClicked = {})
    }
}
