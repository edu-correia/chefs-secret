package com.educorreia.chefssecrets.recipes.recipe_details.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.educorreia.chefssecrets.R
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeUIModel
import com.educorreia.chefssecrets.recipes.recipe_details.presentation.BOTTOM_SHEET_MIN_HEIGHT

@Composable
fun RecipeDetaisSheetContent(
    recipe: RecipeUIModel?,
    isBottomSheetClosed: Boolean = false,
    maxHeightFraction: Float = 1f
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = maxHeightFraction)
            .background(AppTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            ExpandHintMessage(showHintMessage = isBottomSheetClosed)

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                style = AppTheme.typography.titleLarge,
                text = "Mac & Cheese",
                color = AppTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                style = AppTheme.typography.body,
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel lorem nec ante tincidunt malesuada. Nunc porttitor, orci quis tristique vehicula, erat quam iaculis nibh, non ornare justo ex vel lacus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Duis.",
                color = AppTheme.colorScheme.onBackground,
                maxLines = if (isBottomSheetClosed) 3 else Int.MAX_VALUE,
                overflow = if (isBottomSheetClosed) TextOverflow.Ellipsis else TextOverflow.Visible
            )

            ConditionalSpacer(showSpacer = isBottomSheetClosed, 80.dp)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(AppTheme.colorScheme.secondary)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SmallRecipeInfo("30 min", R.drawable.ic_clock)
                SmallRecipeInfo("3 servings", R.drawable.ic_serving_plate)
                SmallRecipeInfo("Easy", R.drawable.ic_low_level)
                SmallRecipeInfo("Expensive", R.drawable.ic_price_tag)
            }

            Spacer(Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_carrot),
                    modifier = Modifier.size(28.dp),
                    tint = AppTheme.colorScheme.primary,
                    contentDescription = null
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = "Ingredients:",
                    style = AppTheme.typography.titleSmall,
                    color = AppTheme.colorScheme.primary
                )
            }

            Spacer(Modifier.height(8.dp))

            BulletList(
                items = listOf(
                    "dwkladawklm",
                    "dwkladdwaawklm",
                    "dwkladaadwawklm",
                    "dwkladadawddwklm",
                ),
                lineSpacing = 4.dp,
                style = AppTheme.typography.body.copy(color = AppTheme.colorScheme.onBackground),
            )

            Spacer(Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_cooking_pot),
                    modifier = Modifier.size(28.dp),
                    tint = AppTheme.colorScheme.primary,
                    contentDescription = null
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = "Cooking instructions:",
                    style = AppTheme.typography.titleSmall,
                    color = AppTheme.colorScheme.primary
                )
            }

            Spacer(Modifier.height(8.dp))

            BulletList(
                items = listOf(
                    "dwkladawklm",
                    "dwkladdwaawklm",
                    "dwkladaadwawklm",
                    "dwkladadawddwklm",
                ),
                lineSpacing = 4.dp,
                style = AppTheme.typography.body.copy(color = AppTheme.colorScheme.onBackground),
            )

            // TODO: Add tags

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Created yesterday",
                style = AppTheme.typography.bodySmall,
                color = AppTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Updated 25 minutes ago",
                style = AppTheme.typography.bodySmall,
                color = AppTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecipeDetaisSheetContentPreview() {
    val isBottomSheetClosed = false
    AppTheme {
        Box(Modifier
            .height(if(isBottomSheetClosed) BOTTOM_SHEET_MIN_HEIGHT else Float.MAX_VALUE.dp)
        ) {
            RecipeDetaisSheetContent(
                recipe = null,
                isBottomSheetClosed = isBottomSheetClosed
            )
        }
    }
}