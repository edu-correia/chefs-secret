package com.educorreia.chefssecrets.recipes.recipe_details.presentation.composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.toSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberParallaxConnection(
    sheetState: SheetState,
    sheetPeekHeight: Dp,
    parallaxFactor: Float = 2f
): Pair<State<Float>, Modifier> {
    var parentSize by remember { mutableStateOf(Size.Zero) }

    val sheetPeekHeightInPx = with(LocalDensity.current) { sheetPeekHeight.toPx() }

    val imageOffsetPx = remember {
        derivedStateOf {
            if (parentSize == Size.Zero) return@derivedStateOf 0f

            val startY = parentSize.height - sheetPeekHeightInPx
            val currentY = sheetState.requireOffset()
            val sheetDeltaY = startY - currentY
            val endY = 0f
            val totalTravelDistance = startY - endY
            val clampedDeltaY = sheetDeltaY.coerceIn(0f, totalTravelDistance)

            -(clampedDeltaY / parallaxFactor)
        }
    }

    val measurementModifier = Modifier.onGloballyPositioned { coordinates ->
        if (parentSize == Size.Zero) {
            parentSize = coordinates.size.toSize()
        }
    }

    return imageOffsetPx to measurementModifier
}