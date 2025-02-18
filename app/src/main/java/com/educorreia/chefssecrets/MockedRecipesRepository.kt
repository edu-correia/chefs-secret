package com.educorreia.chefssecrets

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