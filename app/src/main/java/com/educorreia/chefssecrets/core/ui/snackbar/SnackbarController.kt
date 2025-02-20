package com.educorreia.chefssecrets.core.ui.snackbar

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

enum class SnackDuration {
    SHORT,
    LONG
}

data class SnackbarEvent(
    val message: String,
    val icon: ImageVector? = null,
    val action: SnackbarAction? = null,
    val duration: SnackDuration? = null,
    val containerColor: Color? = null
)

data class SnackbarAction(
    val name: String,
    val action: () -> Unit
)

object SnackbarController {
    private val _events = Channel<SnackbarEvent>()
    val events = _events.receiveAsFlow()

    suspend fun sendEvent(event: SnackbarEvent) {
        _events.send(event)
    }
}