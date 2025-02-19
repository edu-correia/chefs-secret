package com.educorreia.chefssecrets

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecipeDetailsViewModel(private val repository: RecipesRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(repository.getRecipes().size)
    val uiState = _uiState.asStateFlow()
}