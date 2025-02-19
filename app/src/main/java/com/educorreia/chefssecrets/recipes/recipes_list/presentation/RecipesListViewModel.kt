package com.educorreia.chefssecrets.recipes.recipes_list.presentation

import androidx.lifecycle.ViewModel
import com.educorreia.chefssecrets.recipes.common.domain.interfaces.RecipesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecipesListViewModel(private val repository: RecipesRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(RecipesListUiState(repository.getRecipes()))
    val uiState = _uiState.asStateFlow()
}