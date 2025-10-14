package com.example.vasilkhusainovtest.data

import com.example.vasilkhusainovtest.data.network.GitHubService
import com.example.vasilkhusainovtest.domain.SearchInfoGitHubRepository
import com.example.vasilkhusainovtest.domain.models.TreeItem
import com.example.vasilkhusainovtest.domain.models.Type
import com.example.vasilkhusainovtest.domain.models.UnifiedItem
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class ImplSearchInfoGitHubRepository(private val apiService: GitHubService) :
    SearchInfoGitHubRepository {

    override suspend fun getSearchItems(query: String): List<UnifiedItem> = coroutineScope {

        val userDeferred = async { apiService.searchUsers(query) }
        val repoDeferred = async { apiService.searchRepository(query) }

        val userResponse = userDeferred.await()
        val repoResponse = repoDeferred.await()

        val unifiedUsers = userResponse.items.map { it.toUnifiedItem() }
        val unifiedRepositories = repoResponse.items.map { it.toUnifiedItem() }

        val combinedList: List<UnifiedItem> = unifiedUsers + unifiedRepositories

        return@coroutineScope combinedList.sortedBy { item ->

            item.name?.uppercase() ?: ""
        }
    }

    override suspend fun getTreeRepository(
        ownerName: String,
        repositoryName: String,
        path: String,
    ): List<TreeItem> {
        val allItems = apiService.getRepositoryContents(
            ownerName,
            repositoryName,
            path,
        ).map { it.toTreeItem() }

        val (folderList, fileList) = allItems.partition { it.typeObject == Type.Folder }

        val sortedFolders = folderList.sortedBy { it.name?.uppercase() ?: "" }
        val sortedFiles = fileList.sortedBy { it.name?.uppercase() ?: "" }

        return sortedFolders + sortedFiles
    }


}