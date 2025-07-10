package com.educorreia.chefssecrets.recipes.create_recipe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educorreia.chefssecrets.R
import com.educorreia.chefssecrets.core.ui.auth.Authenticator
import com.educorreia.chefssecrets.core.ui.navigation.Navigator
import com.educorreia.chefssecrets.core.ui.utils.UiText
import com.educorreia.chefssecrets.core.ui.utils.UiText.StringResource
import com.educorreia.chefssecrets.recipes.create_recipe.domain.exceptions.ValidationException
import com.educorreia.chefssecrets.recipes.create_recipe.domain.exceptions.ValidationException.*
import com.educorreia.chefssecrets.recipes.create_recipe.domain.use_cases.ValidateRecipeDescription
import com.educorreia.chefssecrets.recipes.create_recipe.domain.use_cases.ValidateRecipeImageUrl
import com.educorreia.chefssecrets.recipes.create_recipe.domain.use_cases.ValidateRecipeName
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CreateRecipeViewModel(
    private val navigator: Navigator,
    private val authenticator: Authenticator
) : ViewModel() {
    private var _uiState = MutableStateFlow(CreateRecipeUiState())
    val uiState = _uiState.asStateFlow()

    private val validationErrorChannel = Channel<UiText>()
    val validationError = validationErrorChannel.receiveAsFlow()

    fun onEvent(event: CreateRecipeEvent) {
        when (event) {
            is CreateRecipeEvent.NameChanged -> {
                _uiState.value = _uiState.value.copy(name = event.name)
            }
            is CreateRecipeEvent.DescriptionChanged -> {
                _uiState.value = _uiState.value.copy(description = event.description)
            }
            is CreateRecipeEvent.ImageUrlChanged -> {
                _uiState.value = _uiState.value.copy(image = event.imageUrl)
            }
            is CreateRecipeEvent.GoBack -> {
                viewModelScope.launch {
                    navigator.goBack()
                }
            }
            // CreateRecipeEvent.Submit -> submit()
            CreateRecipeEvent.Submit -> {
                viewModelScope.launch {
                    authenticator.logout()
                }
            }
        }
    }

    private fun submit() {
        val state = _uiState.value

        try {
            ValidateRecipeName().execute(state.name)
            ValidateRecipeDescription().execute(state.description)
            ValidateRecipeImageUrl().execute(state.image)
        } catch (e: ValidationException) {
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
                validationErrorChannel.send(errorMessage)
            }
        }
    }
}