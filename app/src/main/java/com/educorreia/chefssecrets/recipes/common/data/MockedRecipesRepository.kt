package com.educorreia.chefssecrets.recipes.common.data

import com.educorreia.chefssecrets.recipes.common.domain.interfaces.RecipesRepository
import com.educorreia.chefssecrets.recipes.common.domain.models.RecipeItem

object MockedRecipesRepository : RecipesRepository {
    override fun getRecipes(): List<RecipeItem> {
        return listOf(
            RecipeItem(
                id = "123",
                title = "Caesar's salad",
                description = "Lorem ipsum dolor asit met."
            ),
            RecipeItem(
                id = "456",
                title = "Mac & Cheese",
                description = "Delicious combination of macaroni and parmesan cheese."
            ),
            RecipeItem(
                id = "123",
                title = "Caesar's salad",
                description = "Lorem ipsum dolor asit met."
            ),
            RecipeItem(
                id = "456",
                title = "Mac & Cheese",
                description = "Delicious combination of macaroni and parmesan cheese."
            ),
            RecipeItem(
                id = "123",
                title = "Caesar's salad",
                description = "Lorem ipsum dolor asit met."
            ),
            RecipeItem(
                id = "456",
                title = "Mac & Cheese",
                description = "Delicious combination of macaroni and parmesan cheese."
            ),
            RecipeItem(
                id = "123",
                title = "Caesar's salad",
                description = "Lorem ipsum dolor asit met."
            ),
            RecipeItem(
                id = "456",
                title = "Mac & Cheese",
                description = "Delicious combination of macaroni and parmesan cheese."
            ),
        )
    }
}