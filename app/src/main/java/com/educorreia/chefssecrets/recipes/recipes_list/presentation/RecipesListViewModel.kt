package com.educorreia.chefssecrets.recipes.recipes_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.educorreia.chefssecrets.recipes.common.domain.interfaces.RecipesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecipesListViewModel(private val repository: RecipesRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(repository.getRecipes())
    val uiState = _uiState.asStateFlow()

    class Factory(private val repository: RecipesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RecipesListViewModel(repository) as T
        }
    }
}