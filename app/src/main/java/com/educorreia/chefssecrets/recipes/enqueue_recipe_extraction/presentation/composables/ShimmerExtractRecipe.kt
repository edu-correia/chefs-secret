package com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.educorreia.chefssecrets.R
import com.educorreia.chefssecrets.core.ui.composables.shimmerEffect
import com.educorreia.chefssecrets.core.ui.theme.AclonicaFont
import com.educorreia.chefssecrets.core.ui.theme.AppTheme

@Composable
fun ShimmerExtractRecipe() {
    Box(
        modifier = Modifier
            .width(240.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(32.dp))
            .shimmerEffect()
            .padding(24.dp),
    )
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ShimmerExtractRecipePreview() {
    AppTheme {
        ShimmerExtractRecipe ()
    }
}
