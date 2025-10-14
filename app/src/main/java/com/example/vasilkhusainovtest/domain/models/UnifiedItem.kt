package com.example.vasilkhusainovtest.domain.models

sealed interface UserOrRepositorySelector{
    data object User:UserOrRepositorySelector
    data object Repository:UserOrRepositorySelector
}

sealed interface UnifiedItem {
    val id: Int?
    val name: String?
    val htmlUrl: String?
    val ownerName: String?

    val avatarUrl: String?
    val fullName: String?
    val description: String?
    val createdAt: String?
    val updatedAt: String?
    val stargazersCount: String?
    val forksCount: String?
    val watchersCount: String?

    val selector: UserOrRepositorySelector
    val isExpanded: Boolean

    fun withExpanded(expanded: Boolean): UnifiedItem

}