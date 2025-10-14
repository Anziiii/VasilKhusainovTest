package com.example.vasilkhusainovtest.ui.treeScreen

import com.example.vasilkhusainovtest.domain.models.TreeItem

data class State(
    val titleHeader: String? = null,
    val contentStatus: ContentStatus = ContentStatus.Loading,
    val items: List<TreeItem> = listOf()
)

sealed interface ContentStatus {
    data object Success : ContentStatus
    data object Loading : ContentStatus
    data object EmptyContent : ContentStatus
    data object Error : ContentStatus
}