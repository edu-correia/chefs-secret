package com.educorreia.chefssecrets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.educorreia.chefssecrets.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    private val viewModel: RecipesListViewModel by viewModels {
        RecipesListViewModel.Factory(MockedRecipesRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme () {
                SystemBarColor(
                    color = AppTheme.colorScheme.secondary,
                    isLightIcons = false
                )

                val items = viewModel.uiState.collectAsState()

                MyApp(items.value)
            }
        }
    }
}

@Composable
fun MyApp(items: List<RecipeItem>) {
    Scaffold(containerColor = AppTheme.colorScheme.background) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Header()
            RecipesList(items)
        }
    }
}

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(
                8.dp, shape = RoundedCornerShape(
                    bottomStart = 24.dp,
                    bottomEnd = 24.dp
                )
            )
            .background(AppTheme.colorScheme.secondary)
            .padding(24.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Welcome back,\nEduardo"
            )

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
        }

    }
}

@Composable
fun RecipesList(items: List<RecipeItem>) {
    LazyColumn(
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 24.dp)
            .background(AppTheme.colorScheme.background)
    ) {
        item {
            Column {
                Spacer(modifier = Modifier.height(16.dp))

                Text("My recipes")

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        items(items.size) { index ->
            val recipe = items[index]
            RecipeTile(recipe)
        }
    }
}

@Composable
fun RecipeTile(recipe: RecipeItem) {
    Box(
        modifier = Modifier
            .padding(bottom = 24.dp)
            .fillMaxWidth()
            .height(160.dp)
            .shadow(
                elevation = 8.dp,
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
                    text = recipe.title
                )

                Text(
                    modifier = Modifier,
                    style = AppTheme.typography.body,
                    text = recipe.description
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
                        text = "Jane Doe"
                    )
                }
            }
            SubcomposeAsyncImage(
                model = "https://i.imgur.com/4gQNMF3.png",
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

@Preview(showBackground = true)
@Composable
fun PagePreview() {
    AppTheme {
        MyApp(
            listOf(
                RecipeItem("abc", "abc", "abcde"),
                RecipeItem("abc", "abc", "abcde"),
                RecipeItem("abc", "abc", "abcde"),
                RecipeItem("abc", "abc", "abcde"),
                RecipeItem("abc", "abc", "abcde")
            )
        )
    }
}