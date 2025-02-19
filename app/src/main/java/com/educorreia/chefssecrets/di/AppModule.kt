package com.educorreia.chefssecrets.di

import com.educorreia.chefssecrets.recipes.recipes_list.presentation.RecipesListViewModel
import com.educorreia.chefssecrets.recipes.common.domain.interfaces.RecipesRepository
import com.educorreia.chefssecrets.recipes.common.data.MockedRecipesRepository
import com.educorreia.chefssecrets.recipes.recipe_details.presentation.RecipeDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<RecipesRepository> {
        MockedRecipesRepository
    }

    viewModelOf(::RecipesListViewModel)
    viewModelOf(::RecipeDetailsViewModel)
}