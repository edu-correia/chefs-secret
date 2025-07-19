package com.educorreia.chefssecrets.core.ui.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class CustomSnackbarVisuals(
    override val message: String,
    override val actionLabel: String? = null,
    override val withDismissAction: Boolean = false,
    override val duration: SnackbarDuration = if (actionLabel == null) SnackbarDuration.Short
    else SnackbarDuration.Indefinite,

    // Custom snackbar properties
    val icon: ImageVector? = null
) : SnackbarVisuals