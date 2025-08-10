package com.educorreia.chefssecrets.recipes.recipes_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educorreia.chefssecrets.core.data.domain.interfaces.RecipesRepository
import com.educorreia.chefssecrets.core.data.domain.models.User
import com.educorreia.chefssecrets.core.ui.auth.Authenticator
import com.educorreia.chefssecrets.core.ui.auth.UserState
import com.educorreia.chefssecrets.core.ui.navigation.Navigator
import com.educorreia.chefssecrets.core.ui.navigation.Route
import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeSummaryUIModel
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

    val userState: StateFlow<UserState> = authenticator.userState

    init {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                recipesList = recipesRepository.getRecipes().map {
                    RecipeSummaryUIModel(it.id, it.title, it.description, it.photoUrl, null)
                }
            )
        }
    }

    fun onEvent(event: RecipesListAction) {
        when (event) {
            is RecipesListAction.GoToCreateRecipePage -> {
                viewModelScope.launch {
                    navigator.navigate(Route.CreateRecipeRoute)
                }
            }
            is RecipesListAction.GoToRecipeDetailsPage -> {
                val id = event.id
                viewModelScope.launch {
                    navigator.navigate(Route.RecipeDetailsRoute(id))
                }
            }
        }
    }
}