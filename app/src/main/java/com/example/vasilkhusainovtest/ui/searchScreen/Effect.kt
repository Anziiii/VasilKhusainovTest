package com.example.vasilkhusainovtest.ui.searchScreen

import androidx.compose.runtime.Immutable

@Immutable
sealed class Effect {
    data class NavigateToWebView(val url: String) : Effect()
    data class NavigateToTreeRepository(val owner: String, val repositoryName: String) : Effect()
    data object NavigateBack : Effect()
}
