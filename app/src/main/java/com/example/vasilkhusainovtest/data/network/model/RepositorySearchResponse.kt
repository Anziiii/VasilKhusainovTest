package com.example.vasilkhusainovtest.data.network.model

import com.example.vasilkhusainovtest.common.convertIsoToCustomFormat
import com.example.vasilkhusainovtest.common.formatCount
import com.example.vasilkhusainovtest.domain.models.ItemRepository
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositorySearchResponse(

    @SerialName("total_count") val totalCount: Int? = null,
    @SerialName("incomplete_results") val incompleteResults: Boolean? = null,
    @SerialName("items") val items: List<ItemsRepositorySearch> = listOf()

)

@Serializable
data class ItemsRepositorySearch(

    @SerialName("id") val id: Int? = null,
    @SerialName("node_id") val nodeId: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("full_name") val fullName: String? = null,
    @SerialName("owner") var owner: Owner? = Owner(),
    @SerialName("private") val private: Boolean? = null,
    @SerialName("html_url") val htmlUrl: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("fork") val fork: Boolean? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("forks_url") val forksUrl: String? = null,
    @SerialName("keys_url") val keysUrl: String? = null,
    @SerialName("collaborators_url") val collaboratorsUrl: String? = null,
    @SerialName("teams_url") val teamsUrl: String? = null,
    @SerialName("hooks_url") val hooksUrl: String? = null,
    @SerialName("issue_events_url") val issueEventsUrl: String? = null,
    @SerialName("events_url") val eventsUrl: String? = null,
    @SerialName("assignees_url") val assigneesUrl: String? = null,
    @SerialName("branches_url") val branchesUrl: String? = null,
    @SerialName("tags_url") val tagsUrl: String? = null,
    @SerialName("blobs_url") val blobsUrl: String? = null,
    @SerialName("git_tags_url") val gitTagsUrl: String? = null,
    @SerialName("git_refs_url") val gitRefsUrl: String? = null,
    @SerialName("trees_url") val treesUrl: String? = null,
    @SerialName("statuses_url") val statusesUrl: String? = null,
    @SerialName("languages_url") val languagesUrl: String? = null,
    @SerialName("stargazers_url") val stargazersUrl: String? = null,
    @SerialName("contributors_url") val contributorsUrl: String? = null,
    @SerialName("subscribers_url") val subscribersUrl: String? = null,
    @SerialName("subscription_url") val subscriptionUrl: String? = null,
    @SerialName("commits_url") val commitsUrl: String? = null,
    @SerialName("git_commits_url") val gitCommitsUrl: String? = null,
    @SerialName("comments_url") val commentsUrl: String? = null,
    @SerialName("issue_comment_url") val issueCommentUrl: String? = null,
    @SerialName("contents_url") val contentsUrl: String? = null,
    @SerialName("compare_url") val compareUrl: String? = null,
    @SerialName("merges_url") val mergesUrl: String? = null,
    @SerialName("archive_url") val archiveUrl: String? = null,
    @SerialName("downloads_url") val downloadsUrl: String? = null,
    @SerialName("issues_url") val issuesUrl: String? = null,
    @SerialName("pulls_url") val pullsUrl: String? = null,
    @SerialName("milestones_url") val milestonesUrl: String? = null,
    @SerialName("notifications_url") val notificationsUrl: String? = null,
    @SerialName("labels_url") val labelsUrl: String? = null,
    @SerialName("releases_url") val releasesUrl: String? = null,
    @SerialName("deployments_url") val deploymentsUrl: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null,
    @SerialName("pushed_at") val pushedAt: String? = null,
    @SerialName("git_url") val gitUrl: String? = null,
    @SerialName("ssh_url") val sshUrl: String? = null,
    @SerialName("clone_url") val cloneUrl: String? = null,
    @SerialName("svn_url") val svnUrl: String? = null,
    @SerialName("homepage") val homepage: String? = null,
    @SerialName("size") val size: Int? = null,
    @SerialName("stargazers_count") val stargazersCount: Int? = null,
    @SerialName("watchers_count") val watchersCount: Int? = null,
    @SerialName("language") val language: String? = null,
    @SerialName("has_issues") val hasIssues: Boolean? = null,
    @SerialName("has_projects") val hasProjects: Boolean? = null,
    @SerialName("has_downloads") val hasDownloads: Boolean? = null,
    @SerialName("has_wiki") val hasWiki: Boolean? = null,
    @SerialName("has_pages") val hasPages: Boolean? = null,
    @SerialName("has_discussions") val hasDiscussions: Boolean? = null,
    @SerialName("forks_count") val forksCount: Int? = null,
    @SerialName("mirror_url") val mirrorUrl: String? = null,
    @SerialName("archived") val archived: Boolean? = null,
    @SerialName("disabled") val disabled: Boolean? = null,
    @SerialName("open_issues_count") val openIssuesCount: Int? = null,
    @SerialName("allow_forking") val allowForking: Boolean? = null,
    @SerialName("is_template") val isTemplate: Boolean? = null,
    @SerialName("web_commit_signoff_required") val webCommitSignoffRequired: Boolean? = null,
    @SerialName("topics") val topics: ArrayList<String> = arrayListOf(),
    @SerialName("visibility") val visibility: String? = null,
    @SerialName("forks") val forks: Int? = null,
    @SerialName("open_issues") val openIssues: Int? = null,
    @SerialName("watchers") val watchers: Int? = null,
    @SerialName("default_branch") val defaultBranch: String? = null,
    @SerialName("score") val score: Double? = null,

    ) {

    fun toUnifiedItem(): ItemRepository = ItemRepository(
        id = this.id,
        name = this.name,
        avatarUrl = this.owner?.avatarUrl,
        htmlUrl = this.htmlUrl,
        ownerName = this.owner?.login,
        description = this.description,
        fullName = this.fullName,
        createdAt = convertIsoToCustomFormat(this.createdAt),
        updatedAt = convertIsoToCustomFormat(this.updatedAt),
        stargazersCount = formatCount(this.stargazersCount),
        watchersCount = formatCount(this.watchersCount),
        forksCount = formatCount(this.forks),
    )
}

@Serializable
data class Owner(

    @SerialName("login") var login: String? = null,
    @SerialName("id") var id: Int? = null,
    @SerialName("node_id") var nodeId: String? = null,
    @SerialName("avatar_url") var avatarUrl: String? = null,
    @SerialName("gravatar_id") var gravatarId: String? = null,
    @SerialName("url") var url: String? = null,
    @SerialName("html_url") var htmlUrl: String? = null,
    @SerialName("followers_url") var followersUrl: String? = null,
    @SerialName("following_url") var followingUrl: String? = null,
    @SerialName("gists_url") var gistsUrl: String? = null,
    @SerialName("starred_url") var starredUrl: String? = null,
    @SerialName("subscriptions_url") var subscriptionsUrl: String? = null,
    @SerialName("organizations_url") var organizationsUrl: String? = null,
    @SerialName("repos_url") var reposUrl: String? = null,
    @SerialName("events_url") var eventsUrl: String? = null,
    @SerialName("received_events_url") var receivedEventsUrl: String? = null,
    @SerialName("type") var type: String? = null,
    @SerialName("user_view_type") var userViewType: String? = null,
    @SerialName("site_admin") var siteAdmin: Boolean? = null

)