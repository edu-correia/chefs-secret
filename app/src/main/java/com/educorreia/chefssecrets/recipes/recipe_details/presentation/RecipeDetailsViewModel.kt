package com.educorreia.chefssecrets.recipes.recipe_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educorreia.chefssecrets.core.data.domain.interfaces.RecipesRepository
import com.educorreia.chefssecrets.core.ui.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(
    private val repository: RecipesRepository,
    private val navigator: Navigator,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeDetailsUiState())
    val uiState = _uiState.asStateFlow()

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