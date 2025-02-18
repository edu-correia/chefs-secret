package com.educorreia.chefssecrets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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