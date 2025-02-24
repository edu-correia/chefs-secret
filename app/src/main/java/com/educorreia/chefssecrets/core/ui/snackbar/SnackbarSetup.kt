package com.educorreia.chefssecrets.core.ui.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import com.educorreia.chefssecrets.core.ui.utils.ObserveAsEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SnackbarSetup(
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {
    ObserveAsEvents(
        flow = SnackbarController.events,
        snackbarHostState
    ) { event ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()

            val result = snackbarHostState.showSnackbar(
                CustomSnackbarVisuals(
                    message = event.message,
                    actionLabel = event.action?.name,
                    duration = if (event.duration == null ||
                        event.duration.equals(SnackDuration.SHORT)) SnackbarDuration.Short
                                else SnackbarDuration.Long,
                    icon = event.icon
                )
            )

            if(result == SnackbarResult.ActionPerformed) {
                event.action?.action?.invoke()
            }
        }
    }
}