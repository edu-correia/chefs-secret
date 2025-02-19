package com.educorreia.chefssecrets.recipes.recipe_details.presentation

import androidx.lifecycle.ViewModel
import com.educorreia.chefssecrets.recipes.common.domain.interfaces.RecipesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecipeDetailsViewModel(private val repository: RecipesRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeDetailsUiState(repository.getRecipes().size))
    val uiState = _uiState.asStateFlow()
}