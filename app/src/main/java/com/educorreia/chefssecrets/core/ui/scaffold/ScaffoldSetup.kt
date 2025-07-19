package com.educorreia.chefssecrets.core.ui.scaffold

import androidx.compose.material3.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun ScaffoldSetup(
    topBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
) {
    val setScaffoldConfiguration = LocalScaffoldConfiguration.current

    LaunchedEffect(Unit) {
        setScaffoldConfiguration(
            ScaffoldConfiguration(
                topBar = topBar,
                floatingActionButton = floatingActionButton,
                floatingActionButtonPosition = floatingActionButtonPosition
            )
        )
    }
}