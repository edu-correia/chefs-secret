package com.educorreia.chefssecrets.recipes.recipe_details.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.educorreia.chefssecrets.R
import com.educorreia.chefssecrets.core.ui.navigation.Route.RecipeDetailsRoute
import com.educorreia.chefssecrets.core.ui.scaffold.ScaffoldSetup
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeUIModel
import com.educorreia.chefssecrets.recipes.common.domain.models.UserSummaryUIModel
import com.educorreia.chefssecrets.recipes.recipe_details.presentation.composables.RecipeDetaisSheetContent
import com.educorreia.chefssecrets.recipes.recipe_details.presentation.composables.rememberParallaxConnection
import org.koin.androidx.compose.koinViewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailsScreenRoot(
    viewModel: RecipeDetailsViewModel = koinViewModel<RecipeDetailsViewModel>(),
    arguments: RecipeDetailsRoute
) {
    val uiState = viewModel.uiState.collectAsState()

    ScaffoldSetup()

    val sheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded,
        skipHiddenState = true
    )
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val sheetPeekHeight = 120.dp
    val (imageOffsetPx, measurementModifier) = rememberParallaxConnection(
        sheetState = sheetState,
        sheetPeekHeight = sheetPeekHeight,
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        modifier = measurementModifier,
        sheetPeekHeight = sheetPeekHeight,
        sheetContainerColor = MaterialTheme.colorScheme.surface,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.75f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    RecipeDetaisSheetContent(recipe = uiState.value.recipe!!)
                }
            }
        },
    ) {
        RecipeDetailsScreen(uiState.value, arguments, imageOffsetPx.value)
    }
}

@Composable
fun RecipeDetailsScreen(
    uiState: RecipeDetailsUiState,
    arguments: RecipeDetailsRoute,
    imageOffset: Float
) {
    Box(modifier = Modifier.fillMaxSize()) {
        SubcomposeAsyncImage(
            model = uiState.recipe?.photoUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .offset {
                    IntOffset(x = 0, y = imageOffset.roundToInt())
                },
            error = {
                Image(
                    painter = painterResource(R.drawable.img_recipe_example),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .offset {
                            IntOffset(x = 0, y = imageOffset.roundToInt())
                        },
                )
            }
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecipeDetailsScreenPreview() {
    AppTheme {
        RecipeDetailsScreen(
            RecipeDetailsUiState(
                RecipeUIModel(
                    id = "123",
                    title = "Spaghetti Bolognese",
                    description = "A hearty Italian classic with a rich meat sauce.",
                    videoUrl = "",
                    photoUrl = "",
                    createdAt = "25 minutes ago",
                    updatedAt = "Last month",
                    ingredients = listOf(
                        "200g spaghetti",
                        "1 onion",
                        "2 garlic cloves",
                        "300g ground beef",
                        "400g canned tomatoes",
                        "olive oil",
                        "salt",
                        "pepper"
                    ),
                    instructions = listOf(
                        "Boil water in a pot and cook spaghetti until al dente.",
                        "Chop onion and garlic, sauté in a pan with olive oil.",
                        "Add ground beef, cook until browned.",
                        "Add canned tomatoes, salt, and pepper. Simmer for 15 minutes.",
                        "Strain spaghetti and mix with the sauce.",
                        "Serve hot with grated cheese if desired."
                    ),
                    duration = 40,
                    servings = 2,
                    cost = "medium",
                    difficulty = "medium",
                    tags = listOf("dinner", "italian", "meat"),
                    owner = UserSummaryUIModel(
                        id = "123",
                        name = "John Doe",
                        photoUrl = "987654321",
                    )
                )
            ),
            RecipeDetailsRoute("1234"),
            10f
        )
    }
}
