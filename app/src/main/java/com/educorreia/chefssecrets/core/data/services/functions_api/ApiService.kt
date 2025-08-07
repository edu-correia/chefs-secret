package com.educorreia.chefssecrets.core.data.services.functions_api

import com.educorreia.chefssecrets.core.data.services.functions_api.requests.RecipeExtractionRequest
import com.educorreia.chefssecrets.core.data.services.functions_api.responses.FetchPreviewResponse
import com.educorreia.chefssecrets.core.data.services.functions_api.responses.RecipeExtractionResponse
import com.educorreia.chefssecrets.core.data.services.functions_api.responses.RecipesListResponse
import com.educorreia.chefssecrets.core.data.services.functions_api.responses.SpecificRecipeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("recipes/my-recipes")
    suspend fun getRecipes(): Response<RecipesListResponse>

    @GET("recipes/by-recipe-id/{recipeId}")
    suspend fun getRecipeById(@Path("recipeId") recipeInt: String): Response<SpecificRecipeResponse>

    @GET("recipes/video-preview")
    suspend fun getVideoPreview(@Query("videoUrl") videoUrl: String): Response<FetchPreviewResponse>

    @POST("recipes/enqueue-extraction")
    suspend fun enqueueRecipeExtraction(@Body request: RecipeExtractionRequest): Response<RecipeExtractionResponse>
}