package com.educorreia.chefssecrets.core.ui.scaffold

import androidx.compose.material3.FabPosition
import androidx.compose.runtime.Composable

data class ScaffoldConfiguration(
    val topBar: @Composable () -> Unit = {},
    val floatingActionButton: @Composable () -> Unit = {},
    val floatingActionButtonPosition: FabPosition = FabPosition.End
)