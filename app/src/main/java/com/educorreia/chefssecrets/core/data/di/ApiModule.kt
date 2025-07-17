package com.educorreia.chefssecrets.core.data.di

import com.educorreia.chefssecrets.core.data.domain.interfaces.RecipesRepository
import com.educorreia.chefssecrets.core.data.services.functions_api.ApiService
import com.educorreia.chefssecrets.core.data.services.functions_api.FunctionsApiRecipesRepository
import com.educorreia.chefssecrets.core.data.services.functions_api.RetrofitClient

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single<OkHttpClient> { RetrofitClient.createOkHttpClient() }

    single<Retrofit> { RetrofitClient.createRetrofit(get()) }

    single<ApiService> { RetrofitClient.createApiService(get()) }

    single<RecipesRepository> { FunctionsApiRecipesRepository(get()) }
}