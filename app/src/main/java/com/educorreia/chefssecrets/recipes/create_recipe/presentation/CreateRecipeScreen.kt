package com.educorreia.chefssecrets.recipes.create_recipe.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.educorreia.chefssecrets.core.ui.scaffold.LocalScaffoldConfiguration
import com.educorreia.chefssecrets.core.ui.scaffold.PreviewScaffold
import com.educorreia.chefssecrets.core.ui.scaffold.ScaffoldConfiguration
import com.educorreia.chefssecrets.core.ui.scaffold.ScaffoldSetup
import com.educorreia.chefssecrets.core.ui.snackbar.CustomSnackbarVisuals
import com.educorreia.chefssecrets.core.ui.snackbar.LocalSnackbarHostState
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.recipes.common.presentation.GoBackHeader
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.CustomTextField
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.FilledButton
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.composables.OutlinedButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateRecipeScreenRoot(
    viewModel: CreateRecipeViewModel = koinViewModel<CreateRecipeViewModel>()
) {
    val uiState = viewModel.uiState.collectAsState()

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

    ScaffoldSetup(
        topBar = {
            GoBackHeader(
                onGoBack = { viewModel.onEvent(CreateRecipeAction.GoBack) },
                text = "Create recipe"
            )
        }
    )

    CreateRecipeScreen(
        uiState = uiState.value,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun CreateRecipeScreen(
    uiState: CreateRecipeUiState,
    onEvent: (CreateRecipeAction) -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        CustomTextField(
            label = "Recipe name",
            value = uiState.name,
            onChange = { value ->
                onEvent(CreateRecipeAction.NameChanged(value))
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomTextField(
            label = "Recipe description",
            value = uiState.description,
            onChange = { value ->
                onEvent(CreateRecipeAction.DescriptionChanged(value))
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomTextField(
            label = "Recipe image URL",
            value = uiState.image,
            onChange = { value ->
                onEvent(CreateRecipeAction.ImageUrlChanged(value))
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        FilledButton(
            text = "Create",
            onClick = {
                onEvent(CreateRecipeAction.Submit)
            },
            modifier = Modifier
                .padding(bottom = 24.dp)
        )
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
                    text = "Create recipe"
                )
            }
        ) {
            CreateRecipeScreen(
                uiState = CreateRecipeUiState("Mac & Cheese"),
                onEvent = {}
            )
        }
    }
}