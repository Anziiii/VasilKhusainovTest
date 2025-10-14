package com.example.vasilkhusainovtest.di

import com.example.vasilkhusainovtest.common.ApiConfig.GITHUB_BASE_URL
import com.example.vasilkhusainovtest.data.ImplSearchInfoGitHubRepository
import com.example.vasilkhusainovtest.data.network.GitHubService
import com.example.vasilkhusainovtest.domain.SearchInfoGitHubRepository
import com.example.vasilkhusainovtest.ui.searchScreen.SearchScreenViewModel
import com.example.vasilkhusainovtest.ui.treeScreen.TreeScreenViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {


    single<GitHubService>{
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val json = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder().baseUrl(GITHUB_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(GitHubService::class.java)
    }

    single<SearchInfoGitHubRepository> {
        ImplSearchInfoGitHubRepository(get())
    }

    viewModel {
        SearchScreenViewModel(get())
    }

    viewModel { params ->
        val ownerName: String = params.get()
        val repositoryName: String = params.get()
        val path: String = params.get()

        TreeScreenViewModel(
            searchInfoGitHubRepository = get(),
            ownerName = ownerName,
            repositoryName = repositoryName,
            path = path
        )
    }



}