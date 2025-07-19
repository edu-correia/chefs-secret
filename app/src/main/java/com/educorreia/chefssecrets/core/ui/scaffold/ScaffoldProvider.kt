package com.educorreia.chefssecrets.core.ui.scaffold

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

val LocalScaffoldConfiguration: ProvidableCompositionLocal
        <(ScaffoldConfiguration) -> Unit> = staticCompositionLocalOf {
    error("No ScaffoldConfiguration provider found")
}