package com.educorreia.chefssecrets.recipes.create_recipe.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.educorreia.chefssecrets.core.ui.scaffold.PreviewScaffold
import com.educorreia.chefssecrets.core.ui.scaffold.ScaffoldSetup
import com.educorreia.chefssecrets.core.ui.snackbar.CustomSnackbarVisuals
import com.educorreia.chefssecrets.core.ui.snackbar.LocalSnackbarHostState
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.GoBackHeader
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.form_steps.RecipeCookingVideoStep
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.form_steps.RecipeIngredientsStep
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.form_steps.RecipeInstructionsStep
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.form_steps.RecipeMediaStep
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.form_steps.RecipeTagsStep
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.form_steps.RecipeTitleAndDescStep
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.form_steps.RecipeUtensilsStep
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateRecipeScreenRoot(
    viewModel: CreateRecipeViewModel = koinViewModel<CreateRecipeViewModel>()
) {
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current
    val snackbarHostState = LocalSnackbarHostState.current

    LaunchedEffect(true) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is CreateRecipeEffect.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        CustomSnackbarVisuals(
                            message = effect.message.asString(context)
                        )
                    )
                }
            }
        }
    }

    val pagerState = rememberPagerState(pageCount = { FormSteps.entries.size })

    LaunchedEffect(uiState.currentPage) {
        pagerState.animateScrollToPage(uiState.currentPage.ordinal)
    }

    ScaffoldSetup(
        topBar = {
            GoBackHeader(
                onGoBack = { viewModel.onEvent(CreateRecipeAction.GoBack) },
                mainText = "Create recipe",
                description = "There you are! Let’s create a fun and intuitive title and description for your recipe!"
            )
        }
    )

    VerticalPager(
        state = pagerState,
        userScrollEnabled = false,
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
    ) {
        CreateRecipeScreen(
            uiState = uiState,
            onEvent = viewModel::onEvent
        )
    }
}

@Composable
fun CreateRecipeScreen(
    uiState: CreateRecipeUiState,
    onEvent: (CreateRecipeAction) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        when (uiState.currentPage) {
            FormSteps.TITLE_AND_DESC -> RecipeTitleAndDescStep(
                goToNextStep = { onEvent(CreateRecipeAction.GoToNextStep) }
            )
            FormSteps.MEDIA -> RecipeMediaStep(
                goToPreviousStep = { onEvent(CreateRecipeAction.GoToPreviousStep) },
                goToNextStep = { onEvent(CreateRecipeAction.GoToNextStep) }
            )
            FormSteps.COOKING_INFO -> RecipeCookingVideoStep(
                goToPreviousStep = { onEvent(CreateRecipeAction.GoToPreviousStep) },
                goToNextStep = { onEvent(CreateRecipeAction.GoToNextStep) }
            )
            FormSteps.INGREDIENTS -> RecipeIngredientsStep(goToNextStep = {onEvent(CreateRecipeAction.GoToNextStep)})
            FormSteps.UTENSILS -> RecipeUtensilsStep(goToNextStep = {onEvent(CreateRecipeAction.GoToNextStep)})
            FormSteps.INSTRUCTIONS -> RecipeInstructionsStep(goToNextStep = {onEvent(CreateRecipeAction.GoToNextStep)})
            FormSteps.TAGS -> RecipeTagsStep(goToNextStep = {})
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun CreateRecipeScreenPreview() {
    AppTheme {
        PreviewScaffold(
            topBar = {
                GoBackHeader(
                    onGoBack = {},
                    mainText = "Create recipe",
                    description = "There you are! Let’s create a fun and intuitive title and description for your recipe!"
                )
            }
        ) {
            CreateRecipeScreen(
                uiState = CreateRecipeUiState(
                    name = "Mac & Cheese"
                ),
                onEvent = {}
            )
        }
    }
}