package com.example.vasilkhusainovtest.ui.treeScreen

import androidx.compose.runtime.Immutable

@Immutable
sealed class Effect {
    data class NavigateToFile(val url: String) : Effect()
    data class NavigateToFolder(
        val ownerName: String,
        val repositoryName: String,
        val path: String,
    ) : Effect()

    data object NavigateBack : Effect()
}