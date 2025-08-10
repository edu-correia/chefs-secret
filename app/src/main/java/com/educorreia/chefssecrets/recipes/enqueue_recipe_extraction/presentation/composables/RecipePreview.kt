package com.educorreia.chefssecrets.recipes.enqueue_recipe_extraction.presentation.composables

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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.educorreia.chefssecrets.R
import com.educorreia.chefssecrets.core.data.domain.models.User
import com.educorreia.chefssecrets.core.ui.theme.AclonicaFont
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.recipes.common.domain.models.VideoPreviewUIModel

@Composable
fun RecipePreview(
    videoUrl: String,
    videoPreview: VideoPreviewUIModel?,
    user: User?,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(4.dp)
            )
            .background(AppTheme.colorScheme.secondary)
            .padding(16.dp)
    ) {
        Row {
            SubcomposeAsyncImage(
                model = videoPreview?.previewImageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(12 / 16f)
                    .clip(RoundedCornerShape(4.dp)),
                error = {
                    Image(
                        painter = painterResource(R.drawable.img_recipe_example),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(12 / 16f)
                            .clip(RoundedCornerShape(4.dp))
                    )
                }
            )

            Spacer(Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = "Recipe preview",
                    modifier = Modifier,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = AclonicaFont,
                        fontWeight = FontWeight.Bold
                    ),
                    color = AppTheme.colorScheme.primary
                )

                Text(
                    modifier = Modifier,
                    style = AppTheme.typography.body,
                    text = videoUrl,
                    color = AppTheme.colorScheme.onBackground
                )

                Row {
                    Text(
                        modifier = Modifier,
                        style = AppTheme.typography.body,
                        text = "Posted by",
                        color = AppTheme.colorScheme.onBackground
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        modifier = Modifier,
                        style = AppTheme.typography.body
                            .copy(fontWeight = FontWeight.Bold),
                        text = "@${videoPreview?.videoOwnerUsername}",
                        color = AppTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    modifier = Modifier,
                    style = AppTheme.typography.body,
                    text = "Creating as:",
                    color = AppTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SubcomposeAsyncImage(
                        model = user?.photoUrl,
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
                        text = user?.name ?: "",
                        style = AppTheme.typography.body,
                        color = AppTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecipePreviewPreview() {
    AppTheme {
        RecipePreview(
            user = User(
                id = "1234",
                name = "Jane Doe",
                email = "test@mail.com",
                photoUrl = "https://i.imgur.com/R0eBtWi.png",
            ),
            videoUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
            videoPreview = VideoPreviewUIModel(
                "https://i.imgur.com/R0eBtWi.png",
                "username"
            ),
        )
    }
}