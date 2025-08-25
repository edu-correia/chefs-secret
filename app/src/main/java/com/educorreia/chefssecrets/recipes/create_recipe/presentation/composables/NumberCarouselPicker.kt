package com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun NumberCarouselPicker(
    modifier: Modifier = Modifier,
    values: List<Int>,
    currentValue: Int,
    onValueChange: (Int) -> Unit,
    itemWidth: Dp = 60.dp,
) {
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // A derived state that efficiently calculates the central item index
    val centralItemIndex by remember {
        derivedStateOf {
            // Don't calculate if the layout info is not ready
            if (!lazyListState.layoutInfo.visibleItemsInfo.indices.isEmpty()) {
                val layoutInfo = lazyListState.layoutInfo
                val visibleItemsInfo = layoutInfo.visibleItemsInfo
                val viewportCenter = layoutInfo.viewportStartOffset + layoutInfo.viewportSize.width / 2

                visibleItemsInfo.minByOrNull {
                    kotlin.math.abs(it.offset + it.size / 2 - viewportCenter)
                }?.index ?: -1
            } else {
                -1
            }
        }
    }

    // Effect to scroll to the initial value when the component is first composed
    // or when the currentValue changes from outside
    LaunchedEffect(key1 = currentValue, key2 = values) {
        val initialIndex = values.indexOf(currentValue).coerceAtLeast(0)
        lazyListState.scrollToItem(initialIndex)
    }

    // Effect to report the new value when the user stops scrolling
    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (!lazyListState.isScrollInProgress && centralItemIndex != -1) {
            val selectedValue = values[centralItemIndex]
            if (selectedValue != currentValue) {
                onValueChange(selectedValue)
                // Animate scroll to the final snapped position for a polished feel
                coroutineScope.launch {
                    lazyListState.animateScrollToItem(centralItemIndex)
                }
            }
        }
    }

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        // Calculate the horizontal padding needed to center the first and last items
        // This is now safe because we have the maxWidth from BoxWithConstraints
        val horizontalPadding = (this.maxWidth / 2) - (itemWidth / 2)

        LazyRow(
            state = lazyListState,
            contentPadding = PaddingValues(horizontal = horizontalPadding),
            verticalAlignment = Alignment.CenterVertically,
            // The snapping behavior
            flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
        ) {
            itemsIndexed(values) { index, value ->
                val isSelected = (index == centralItemIndex)
                CarouselItem(
                    value = value,
                    isSelected = isSelected,
                    itemWidth = itemWidth
                )
            }
        }
    }
}


@Composable
private fun CarouselItem(
    value: Int,
    isSelected: Boolean,
    itemWidth: Dp
) {
    // Animate scale and color based on selection state
    val scale by animateFloatAsState(targetValue = if (isSelected) 1.0f else 0.75f, label = "scale")
    val color by animateColorAsState(
        targetValue = if (isSelected) AppTheme.colorScheme.primary
            else AppTheme.colorScheme.onBackground.copy(alpha = 0.6f),
        label = "color"
    )
    val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
    val alpha by animateFloatAsState(targetValue = if (isSelected) 1.0f else 0.5f, label = "alpha")

    Box(
        modifier = Modifier
            .width(itemWidth)
            .height(itemWidth * 1.2f),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value.toString(),
            style = TextStyle(
                color = color,
                fontSize = (28 * scale).sp,
                fontWeight = fontWeight
            ),
            modifier = Modifier.alpha(alpha)
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun NumberCarouselPickerPreview() {
    AppTheme {
        NumberCarouselPicker(
            values = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
            currentValue = 5,
            onValueChange = {}
        )
    }
}