package com.educorreia.chefssecrets.recipes.recipes_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educorreia.chefssecrets.core.data.domain.interfaces.RecipesRepository
import com.educorreia.chefssecrets.core.data.domain.models.User
import com.educorreia.chefssecrets.core.ui.auth.Authenticator
import com.educorreia.chefssecrets.core.ui.navigation.Navigator
import com.educorreia.chefssecrets.core.ui.navigation.Route.CreateRecipeRoute
import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipesListViewModel(
    private val recipesRepository: RecipesRepository,
    private val navigator: Navigator,
    private val authenticator: Authenticator,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipesListUiState())
    val uiState = _uiState.asStateFlow()

    val currentUser: StateFlow<User?> = authenticator.currentUser

    init {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                recipesList = recipesRepository.getRecipes().map {
                    RecipeItem(it.id, it.title, it.description, it.photoUrl)
                }
            )
        }
    }

    fun onEvent(event: RecipesListEvent) {
        when (event) {
            is RecipesListEvent.GoToCreateRecipePage -> {
                viewModelScope.launch {
                    navigator.navigate(CreateRecipeRoute)
                }
            }
        }
    }
}