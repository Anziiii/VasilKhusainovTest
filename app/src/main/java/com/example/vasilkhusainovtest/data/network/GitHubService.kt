package com.example.vasilkhusainovtest.data.network

import com.example.vasilkhusainovtest.common.ApiConfig.SEARCH_REPOSITORY_ENDPOINT
import com.example.vasilkhusainovtest.common.ApiConfig.SEARCH_USERS_ENDPOINT
import com.example.vasilkhusainovtest.data.network.model.RepositorySearchResponse
import com.example.vasilkhusainovtest.data.network.model.TreeHierarchyResponse
import com.example.vasilkhusainovtest.data.network.model.UserSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

    @GET(SEARCH_USERS_ENDPOINT)
    suspend fun searchUsers(
        @Query("q") query: String,
    ): UserSearchResponse

    @GET(SEARCH_REPOSITORY_ENDPOINT)
    suspend fun searchRepository(
        @Query("q") query: String,
    ): RepositorySearchResponse

    @GET("repos/{owner}/{repositoryName}/contents/{path}")
    suspend fun getRepositoryContents(
        @Path("owner") ownerName: String,
        @Path("repositoryName") repositoryName: String,
        @Path("path", encoded = true) path: String = "",
    ): List<TreeHierarchyResponse>

}