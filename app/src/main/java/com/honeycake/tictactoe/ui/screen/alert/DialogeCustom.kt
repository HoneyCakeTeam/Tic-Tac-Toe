package com.honeycake.tictactoe.ui.screen.alert

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

/**
 * Created by Aziza Helmy on 7/13/2023.
 */
@Composable
fun DialogCustom(onDismiss: () -> Unit,) {
    Dialog(onDismissRequest = onDismiss) {
        DialogAlert(onDismiss = onDismiss, onButtonClicked = {})
    }
}
