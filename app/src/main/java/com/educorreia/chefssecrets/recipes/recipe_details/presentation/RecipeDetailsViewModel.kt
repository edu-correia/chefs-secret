package com.educorreia.chefssecrets.recipes.recipe_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educorreia.chefssecrets.core.data.domain.interfaces.RecipesRepository
import com.educorreia.chefssecrets.core.ui.navigation.Navigator
import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(
    private val recipeId: String,
    private val recipesRepository: RecipesRepository,
    private val navigator: Navigator,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            val recipe = recipesRepository.getRecipeById(recipeId)

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                recipe = RecipeUIModel.fromDomain(recipe)
            )
        }
    }

    fun onEvent(event: RecipeDetailsAction) {
        when (event) {
            is RecipeDetailsAction.GoBack -> {
                viewModelScope.launch {
                    navigator.goBack()
                }
            }
        }
    }
}