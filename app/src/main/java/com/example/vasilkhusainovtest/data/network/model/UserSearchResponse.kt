package com.example.vasilkhusainovtest.data.network.model

import com.example.vasilkhusainovtest.domain.models.ItemUser
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserSearchResponse(

    @SerialName("total_count") val totalCount: Int? = null,
    @SerialName("incomplete_results") val incompleteResults: Boolean? = null,
    @SerialName("items") val items: List<Items> = listOf()
)

@Serializable
data class Items(

    @SerialName("login") val login: String? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("node_id") val nodeId: String? = null,
    @SerialName("avatar_url") val avatarUrl: String? = null,
    @SerialName("gravatar_id") val gravatarId: String? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("html_url") val htmlUrl: String? = null,
    @SerialName("followers_url") val followersUrl: String? = null,
    @SerialName("following_url") val followingUrl: String? = null,
    @SerialName("gists_url") val gistsUrl: String? = null,
    @SerialName("starred_url") val starredUrl: String? = null,
    @SerialName("subscriptions_url") val subscriptionsUrl: String? = null,
    @SerialName("organizations_url") val organizationsUrl: String? = null,
    @SerialName("repos_url") val reposUrl: String? = null,
    @SerialName("events_url") val eventsUrl: String? = null,
    @SerialName("received_events_url") val receivedEventsUrl: String? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("user_view_type") val userViewType: String? = null,
    @SerialName("site_admin") val siteAdmin: Boolean? = null,
    @SerialName("score") val score: Double? = null

) {

    fun toUnifiedItem(): ItemUser = ItemUser(
        id = this.id,
        name = this.login,
        htmlUrl = this.htmlUrl,
        avatarUrl = this.avatarUrl
    )
}