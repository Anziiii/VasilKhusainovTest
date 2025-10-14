package com.example.vasilkhusainovtest.domain

import com.example.vasilkhusainovtest.domain.models.TreeItem
import com.example.vasilkhusainovtest.domain.models.UnifiedItem


interface SearchInfoGitHubRepository {

    suspend fun getSearchItems(query: String): List<UnifiedItem>

    suspend fun getTreeRepository(
        ownerName: String,
        repositoryName: String,
        path: String,
    ): List<TreeItem>

}