package com.example.vasilkhusainovtest.domain.models

data class ItemUser(
    override val id: Int?,
    override val name: String?,
    override val htmlUrl: String?,
    override val ownerName: String? = null,
    override val selector: UserOrRepositorySelector = UserOrRepositorySelector.User,
    override val isExpanded: Boolean = false,

    override val avatarUrl: String? = null,
    override val fullName: String? = null,
    override val description: String? = null,
    override val createdAt: String? = null,
    override val updatedAt: String? = null,
    override val stargazersCount: String? = null,
    override val forksCount: String? = null,
    override val watchersCount: String? = null,
) : UnifiedItem{
    override fun withExpanded(expanded: Boolean): UnifiedItem {
        return this.copy(isExpanded = expanded)
    }
}

