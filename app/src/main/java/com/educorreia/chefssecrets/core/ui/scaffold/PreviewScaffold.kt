package com.educorreia.chefssecrets.core.ui.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PreviewScaffold(
    topBar: @Composable () -> Unit = {},
    fab: @Composable () -> Unit = {},
    fabPosition: FabPosition = FabPosition.End,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = topBar,
        floatingActionButton = fab,
        floatingActionButtonPosition = fabPosition
    ) { pad ->
        Box(modifier = Modifier.padding(pad)) {
            content()
        }
    }
}