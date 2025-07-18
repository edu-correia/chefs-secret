package com.educorreia.chefssecrets.recipes.recipes_list.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.educorreia.chefssecrets.R
import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeItem
import com.educorreia.chefssecrets.core.ui.theme.AppTheme

@Composable
fun RecipeTile(recipe: RecipeItem) {
    Box(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 12.dp)
            .fillMaxWidth()
            .height(160.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(4.dp)
            )
            .background(AppTheme.colorScheme.secondary)
    ) {
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier,
                    style = AppTheme.typography.titleNormal,
                    text = recipe.title,
                    color = AppTheme.colorScheme.primary
                )

                Text(
                    modifier = Modifier,
                    style = AppTheme.typography.body,
                    text = recipe.description,
                    color = AppTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.weight(1f))

                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SubcomposeAsyncImage(
                        model = "https://i.imgur.com/R0eBtWi.png",
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        error = {
                            Image(
                                painter = painterResource(R.drawable.img_user_example),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                            )
                        }
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    Text(
                        modifier = Modifier,
                        style = AppTheme.typography.body,
                        text = "Jane Doe",
                        color = AppTheme.colorScheme.primary
                    )
                }
            }
            SubcomposeAsyncImage(
                model = recipe.photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(12/16f)
                    .clip(RoundedCornerShape(4.dp)),
                error = {
                    Image(
                        painter = painterResource(R.drawable.img_recipe_example),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(12/16f)
                            .clip(RoundedCornerShape(4.dp))
                    )
                }
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecipeTilePreview() {
    AppTheme {
        RecipeTile(
            recipe = RecipeItem(
                id = "123",
                title = "Caesar's salad",
                description = "Lorem ipsum dolor asit met.",
                photoUrl = "https://i.imgur.com/R0eBtWi.png"
            ),
        )
    }
}
