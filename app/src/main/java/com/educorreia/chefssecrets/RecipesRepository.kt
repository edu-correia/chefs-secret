package com.educorreia.chefssecrets

interface RecipesRepository {
    fun getRecipes(): List<RecipeItem>
}