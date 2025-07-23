package com.educorreia.chefssecrets.recipes.recipe_details.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import com.educorreia.chefssecrets.recipes.recipe_details.presentation.composables.CustomSheetDragHandle
import com.educorreia.chefssecrets.recipes.recipe_details.presentation.composables.RecipeDetaisSheetContent
import com.educorreia.chefssecrets.recipes.recipe_details.presentation.composables.rememberParallaxConnection
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import kotlin.math.roundToInt

val BOTTOM_SHEET_MAX_FRACTION = 0.75f
val BOTTOM_SHEET_MIN_HEIGHT = 200.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailsScreenRoot(
    arguments: RecipeDetailsRoute
) {
    val viewModel: RecipeDetailsViewModel = koinViewModel(
        parameters = { parametersOf(arguments.recipeId) }
    )

    val uiState = viewModel.uiState.collectAsState()

    ScaffoldSetup()

    val sheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded,
        skipHiddenState = true
    )
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val sheetPeekHeight = BOTTOM_SHEET_MIN_HEIGHT
    val (imageOffsetPx, measurementModifier) = rememberParallaxConnection(
        sheetState = sheetState,
        sheetPeekHeight = sheetPeekHeight,
    )

    val isBottomSheetClosed by remember {
        derivedStateOf {
            sheetState.currentValue == SheetValue.PartiallyExpanded &&
                    sheetState.targetValue == SheetValue.PartiallyExpanded
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        modifier = measurementModifier,
        sheetPeekHeight = sheetPeekHeight,
        sheetContainerColor = AppTheme.colorScheme.background,
        sheetContent = {
            RecipeDetaisSheetContent(
                recipe = uiState.value.recipe,
                isBottomSheetClosed = isBottomSheetClosed,
                maxHeightFraction = BOTTOM_SHEET_MAX_FRACTION
            )
        },
        sheetDragHandle = { CustomSheetDragHandle() }
    ) {
        RecipeDetailsScreen(uiState.value, viewModel::onEvent, imageOffsetPx.value)
    }
}

@Composable
fun RecipeDetailsScreen(
    uiState: RecipeDetailsUiState,
    onEvent: (RecipeDetailsAction) -> Unit,
    imageOffset: Float
) {
    Box(modifier = Modifier.fillMaxSize()) {
        SubcomposeAsyncImage(
            model =  uiState.recipe?.photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .offset {
                    IntOffset(x = 0, y = imageOffset.roundToInt())
                },
            error = {
                Image(
                    painter = painterResource(R.drawable.img_recipe_example),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .offset {
                            IntOffset(x = 0, y = imageOffset.roundToInt())
                        },
                )
            }
        )

        IconButton(
            onClick = { onEvent(RecipeDetailsAction.GoBack) },
            modifier = Modifier
                .align(Alignment.TopStart)
                .statusBarsPadding()
                .padding(16.dp)
                .background(AppTheme.colorScheme.background, CircleShape)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = AppTheme.colorScheme.primary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecipeDetailsScreenPreview() {
    val recipe = RecipeUIModel(
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
        ),
        instructions = listOf(
            "Boil water in a pot and cook spaghetti until al dente.",
            "Chop onion and garlic, sauté in a pan with olive oil.",
            "Add ground beef, cook until browned.",
        ),
        utensils = listOf(
            "1 spoon",
            "2 cooking pots"
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

    AppTheme {
        BottomSheetScaffold(
            sheetContainerColor = AppTheme.colorScheme.background,
            sheetContent = { RecipeDetaisSheetContent(recipe = recipe, true) },
            sheetDragHandle = { CustomSheetDragHandle() },
            sheetPeekHeight = BOTTOM_SHEET_MIN_HEIGHT
        ) {
            RecipeDetailsScreen(
                uiState = RecipeDetailsUiState(
                    isLoading = false,
                    recipe = recipe
                ),
                onEvent = {},
                imageOffset = 0f
            )
        }
    }
}
