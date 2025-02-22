package com.educorreia.chefssecrets.recipes.create_recipe.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.educorreia.chefssecrets.core.ui.snackbar.SnackbarController
import com.educorreia.chefssecrets.core.ui.snackbar.SnackbarEvent
import com.educorreia.chefssecrets.core.ui.theme.AppTheme
import com.educorreia.chefssecrets.core.ui.theme.SystemBarColor
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
    LaunchedEffect(true) {
        viewModel.validationError.collect { uiText ->
            SnackbarController.sendEvent(SnackbarEvent(uiText.asString(context)))
        }
    }

    CreateRecipeScreen(
        uiState = uiState.value,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun CreateRecipeScreen(
    uiState: CreateRecipeUiState,
    onEvent: (CreateRecipeEvent) -> Unit
) {
    SystemBarColor(color = AppTheme.colorScheme.background)

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
                onEvent(CreateRecipeEvent.NameChanged(value))
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomTextField(
            label = "Recipe description",
            value = uiState.description,
            onChange = { value ->
                onEvent(CreateRecipeEvent.DescriptionChanged(value))
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomTextField(
            label = "Recipe image URL",
            value = uiState.image,
            onChange = { value ->
                onEvent(CreateRecipeEvent.ImageUrlChanged(value))
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        Row {
            OutlinedButton(
                text = "Cancel",
                onClick = {
                    onEvent(CreateRecipeEvent.GoBack)
                },
                modifier = Modifier.weight(1f)
            )

            FilledButton(
                text = "Create",
                onClick = {
                    onEvent(CreateRecipeEvent.Submit)
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun CreateRecipeScreenPreview() {
    AppTheme {
        CreateRecipeScreen(
            uiState = CreateRecipeUiState("Mac & Cheese"),
            onEvent = {}
        )
    }
}