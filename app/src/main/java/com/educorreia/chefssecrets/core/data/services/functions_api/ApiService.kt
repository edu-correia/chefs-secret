package com.educorreia.chefssecrets.core.data.services.functions_api

import com.educorreia.chefssecrets.core.data.services.functions_api.responses.RecipesListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("fetchLoggedUserRecipes")
    suspend fun getRecipes(): Response<RecipesListResponse>
}