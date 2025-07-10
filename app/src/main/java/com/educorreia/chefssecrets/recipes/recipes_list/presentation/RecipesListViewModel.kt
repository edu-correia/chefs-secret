package com.educorreia.chefssecrets.recipes.recipes_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educorreia.chefssecrets.core.data.domain.models.User
import com.educorreia.chefssecrets.core.ui.auth.Authenticator
import com.educorreia.chefssecrets.core.ui.navigation.Navigator
import com.educorreia.chefssecrets.core.ui.navigation.Route.CreateRecipeRoute
import com.educorreia.chefssecrets.recipes.common.domain.interfaces.RecipesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipesListViewModel(
    private val repository: RecipesRepository,
    private val navigator: Navigator,
    private val authenticator: Authenticator
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipesListUiState(repository.getRecipes()))
    val uiState = _uiState.asStateFlow()

    val currentUser: StateFlow<User?> = authenticator.currentUser

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