package com.example.vasilkhusainovtest.ui.searchScreen

import com.example.vasilkhusainovtest.domain.models.UnifiedItem

data class State(
    val panelSearchSelector: PanelSearchSelector = PanelSearchSelector.Closed,
    val searchText: String = "",
    val items: List<UnifiedItem> = listOf(),
    val contentStatus: ContentStatus = ContentStatus.Waiting
)

sealed interface PanelSearchSelector{
    data object Open:PanelSearchSelector
    data object Closed:PanelSearchSelector
}

sealed interface ContentStatus{
    data object Waiting:ContentStatus
    data object Success:ContentStatus
    data object Loading:ContentStatus
    data object EmptyContent:ContentStatus
    data object Error: ContentStatus
}