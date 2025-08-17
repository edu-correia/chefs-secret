package com.educorreia.chefssecrets.recipes.create_recipe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educorreia.chefssecrets.R
import com.educorreia.chefssecrets.core.ui.auth.Authenticator
import com.educorreia.chefssecrets.core.ui.navigation.Navigator
import com.educorreia.chefssecrets.core.ui.utils.UiText.StringResource
import com.educorreia.chefssecrets.recipes.create_recipe.domain.exceptions.CreateRecipeValidationException
import com.educorreia.chefssecrets.recipes.create_recipe.domain.exceptions.CreateRecipeValidationException.*
import com.educorreia.chefssecrets.recipes.create_recipe.domain.use_cases.ValidateRecipeDescription
import com.educorreia.chefssecrets.recipes.create_recipe.domain.use_cases.ValidateRecipeImageUrl
import com.educorreia.chefssecrets.recipes.create_recipe.domain.use_cases.ValidateRecipeName
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateRecipeViewModel(
    private val navigator: Navigator,
    private val authenticator: Authenticator
) : ViewModel() {
    private var _uiState = MutableStateFlow(CreateRecipeUiState())
    val uiState = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<CreateRecipeEffect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: CreateRecipeAction) {
        when (event) {
            is CreateRecipeAction.NameChanged -> {
                _uiState.value = _uiState.value.copy(name = event.name)
            }
            is CreateRecipeAction.DescriptionChanged -> {
                _uiState.value = _uiState.value.copy(description = event.description)
            }
            is CreateRecipeAction.ImageUrlChanged -> {
                _uiState.value = _uiState.value.copy(image = event.imageUrl)
            }
            is CreateRecipeAction.GoBack -> {
                viewModelScope.launch {
                    navigator.goBack()
                }
            }
            CreateRecipeAction.Submit -> submit()
        }
    }

    private fun submit() {
        val state = _uiState.value

        try {
            ValidateRecipeName().execute(state.name)
            ValidateRecipeDescription().execute(state.description)
            ValidateRecipeImageUrl().execute(state.image)
        } catch (e: CreateRecipeValidationException) {
            val errorMessage = when (e::class.java) {
                EmptyRecipeNameException::class.java -> {
                    StringResource(R.string.error_recipe_name_empty)
                }
                EmptyRecipeDescriptionException::class.java -> {
                    StringResource(R.string.error_recipe_description_empty)
                }
                EmptyRecipeImageUrlException::class.java -> {
                    StringResource(R.string.error_recipe_image_url_empty)
                }
                InvalidLinkForImageUrlException::class.java -> {
                    StringResource(R.string.error_recipe_image_url_invalid_link)
                }
                else -> {
                    StringResource(R.string.unknown_error)
                }
            }

            viewModelScope.launch {
                _effect.emit(CreateRecipeEffect.ShowSnackbar(errorMessage))
            }
        }
    }
}