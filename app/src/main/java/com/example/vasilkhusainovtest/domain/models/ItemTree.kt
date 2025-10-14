package com.example.vasilkhusainovtest.domain.models

data class TreeItem(
    val name: String? = null,
    val path: String? = null,
    val sha: String? = null,
    val size: String? = null,
    val url: String? = null,
    val htmlUrl: String? = null,
    val gitUrl: String? = null,
    val downloadUrl: String? = null,
    val type: String? = null,
    val typeObject: Type = Type.Folder,
)

sealed interface Type {
    data object Folder : Type
    data object File : Type
    data object Unknown : Type
}