package com.educorreia.chefssecrets.di

import com.educorreia.chefssecrets.RecipesListViewModel
import com.educorreia.chefssecrets.RecipesRepository
import com.educorreia.chefssecrets.MockedRecipesRepository
import com.educorreia.chefssecrets.RecipeDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<RecipesRepository> {
        MockedRecipesRepository
    }

    viewModelOf(::RecipesListViewModel)
    viewModelOf(::RecipeDetailsViewModel)
}