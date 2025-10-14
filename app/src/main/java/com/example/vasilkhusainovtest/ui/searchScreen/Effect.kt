package com.example.vasilkhusainovtest.ui.searchScreen

sealed class Effect {
    data class NavigateToWebView(val url: String) : Effect()
    data class NavigateToTreeRepository(val owner: String, val repositoryName: String) : Effect()
    data object NavigateBack : Effect()
}
