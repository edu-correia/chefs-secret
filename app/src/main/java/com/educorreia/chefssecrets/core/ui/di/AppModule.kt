package com.educorreia.chefssecrets.core.ui.di

import com.educorreia.chefssecrets.core.ui.navigation.DefaultNavigator
import com.educorreia.chefssecrets.core.ui.navigation.Navigator
import com.educorreia.chefssecrets.core.ui.navigation.Route
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.RecipesListViewModel
import com.educorreia.chefssecrets.recipes.common.domain.interfaces.RecipesRepository
import com.educorreia.chefssecrets.recipes.common.data.MockedRecipesRepository
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.CreateRecipeViewModel
import com.educorreia.chefssecrets.recipes.recipe_details.presentation.RecipeDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<RecipesRepository> {
        MockedRecipesRepository
    }

    single<Navigator> {
        DefaultNavigator(Route.RecipesListRoute)
    }

    viewModelOf(::RecipesListViewModel)
    viewModelOf(::RecipeDetailsViewModel)
    viewModelOf(::CreateRecipeViewModel)
}