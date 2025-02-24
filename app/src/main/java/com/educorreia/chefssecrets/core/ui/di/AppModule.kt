package com.educorreia.chefssecrets.core.ui.di

import com.educorreia.chefssecrets.core.data.domain.interfaces.UserAuthService
import com.educorreia.chefssecrets.core.data.services.firebase_auth.FirebaseUserAuth
import com.educorreia.chefssecrets.core.ui.auth.Authenticator
import com.educorreia.chefssecrets.core.ui.auth.DefaultAuthenticator
import com.educorreia.chefssecrets.core.ui.navigation.DefaultNavigator
import com.educorreia.chefssecrets.core.ui.navigation.Navigator
import com.educorreia.chefssecrets.core.ui.navigation.Route
import com.educorreia.chefssecrets.login.presentation.LoginViewModel
import com.educorreia.chefssecrets.recipes.common.data.MockedRecipesRepository
import com.educorreia.chefssecrets.recipes.common.domain.interfaces.RecipesRepository
import com.educorreia.chefssecrets.recipes.create_recipe.presentation.CreateRecipeViewModel
import com.educorreia.chefssecrets.recipes.recipe_details.presentation.RecipeDetailsViewModel
import com.educorreia.chefssecrets.recipes.recipes_list.presentation.RecipesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<RecipesRepository> {
        MockedRecipesRepository
    }

    single<Navigator> {
        DefaultNavigator(Route.LoginRoute)
    }

    single<Authenticator> {
        DefaultAuthenticator()
    }

    single<UserAuthService> {
        FirebaseUserAuth()
    }

    viewModelOf(::LoginViewModel)
    viewModelOf(::RecipesListViewModel)
    viewModelOf(::RecipeDetailsViewModel)
    viewModelOf(::CreateRecipeViewModel)
}