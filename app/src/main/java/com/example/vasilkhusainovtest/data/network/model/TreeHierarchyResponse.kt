package com.example.vasilkhusainovtest.data.network.model

import com.example.vasilkhusainovtest.common.formatBytes
import com.example.vasilkhusainovtest.common.fromStringToTypeObject
import com.example.vasilkhusainovtest.domain.models.TreeItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TreeHierarchyResponse(

    @SerialName("name") var name: String? = null,
    @SerialName("path") var path: String? = null,
    @SerialName("sha") var sha: String? = null,
    @SerialName("size") var size: Int? = null,
    @SerialName("url") var url: String? = null,
    @SerialName("html_url") var htmlUrl: String? = null,
    @SerialName("git_url") var gitUrl: String? = null,
    @SerialName("download_url") var downloadUrl: String? = null,
    @SerialName("type") var type: String? = null,

    ) {
    fun toTreeItem(): TreeItem = TreeItem(
        name = this.name,
        path = this.path,
        sha = this.sha,
        size = this.size?.formatBytes(),
        url = this.url,
        htmlUrl = this.htmlUrl,
        downloadUrl = this.downloadUrl,
        type = this.type,
        typeObject = fromStringToTypeObject(this.type)
    )
}