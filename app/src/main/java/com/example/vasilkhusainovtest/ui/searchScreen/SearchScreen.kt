package com.example.vasilkhusainovtest.ui.theme

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.vasilkhusainovtest.R
import com.example.vasilkhusainovtest.domain.models.UserOrRepositorySelector
import com.example.vasilkhusainovtest.ui.searchScreen.SearchScreenViewModel
import com.example.vasilkhusainovtest.ui.searchScreen.ContentStatus
import com.example.vasilkhusainovtest.ui.searchScreen.Effect
import com.example.vasilkhusainovtest.ui.searchScreen.components.RepositoryCard
import com.example.vasilkhusainovtest.ui.searchScreen.components.SearchTopBar
import com.example.vasilkhusainovtest.ui.searchScreen.components.ShimmerAnimation
import com.example.vasilkhusainovtest.ui.searchScreen.components.UserCard
import com.example.vasilkhusainovtest.ui.searchScreen.components.Widget
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.example.vasilkhusainovtest.ui.searchScreen.extension.clearFocusOnScrollAndTap
import com.example.vasilkhusainovtest.ui.searchScreen.extension.clearFocusOnTap

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchScreenViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val lazyListState = rememberLazyListState()

    val lifecycleOwner = LocalLifecycleOwner.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .clearFocusOnTap(),
        topBar = {
            SearchTopBar(
                panelSearchSelector = state.panelSearchSelector,
                textValue = state.searchText,
                onValueChange = viewModel::eventChangeText,
                onBackClicked = { navController.popBackStack() },
                onSearchClicked = viewModel::eventOpenSearchPanel
            )
        },
        containerColor = LocalCustomColorsPalette.current.backgroundColor
    ) { innerPadding ->

        LaunchedEffect(Unit) {

            lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect { event ->
                    android.util.Log.d("LOGGER", "eerewrewr")
                    when (event) {
                        Effect.NavigateBack -> navController.popBackStack()
                        is Effect.NavigateToTreeRepository -> {
                            navController.navigate("repository/${event.owner}/${event.repositoryName}")
                        }

                        is Effect.NavigateToWebView -> {
                            val encodedUrl = Uri.encode(event.url)
                            navController.navigate("webViewScreen/$encodedUrl")
                        }
                    }
                }
            }
        }

        when (state.contentStatus) {
            ContentStatus.Waiting -> {
                Widget(
                    innerPadding = innerPadding,
                    titleText = R.string.search,
                    descriptionText = R.string.title_search,
                    animationResource = R.raw.wait_search_animation
                )
            }

            ContentStatus.Error -> {
                Widget(
                    innerPadding = innerPadding,
                    titleText = R.string.error_content_title,
                    descriptionText = R.string.error_content_description,
                    animationResource = R.raw.error,
                    isEventSupport = true,
                    event = viewModel::eventUpdatePage,
                    titleEvent = R.string.error_event_title
                )
            }

            ContentStatus.EmptyContent -> {
                Widget(
                    innerPadding = innerPadding,
                    titleText = R.string.empty_items_title,
                    descriptionText = R.string.empty_items_description,
                    animationResource = R.raw.not_found_animation
                )
            }

            ContentStatus.Loading -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    items(10) {
                        ShimmerAnimation(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .height(80.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            shimmerColors = listOf(
                                LocalCustomColorsPalette.current.shimmerEffectColor.primaryGradientColor,
                                LocalCustomColorsPalette.current.shimmerEffectColor.shimmerColor,
                                LocalCustomColorsPalette.current.shimmerEffectColor.secondaryGradientColor
                            )
                        )
                    }
                }
            }

            ContentStatus.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .clearFocusOnScrollAndTap(lazyListState),
                    state = lazyListState
                ) {
                    items(state.items) {
                        when (it.selector) {
                            UserOrRepositorySelector.Repository ->
                                RepositoryCard(
                                    id = it.id ?: 0,
                                    name = it.name ?: "",
                                    companyName = it.fullName ?: "",
                                    description = it.description ?: "",
                                    image = it.avatarUrl ?: "",
                                    textUpdate = it.updatedAt ?: "",
                                    textCreate = it.createdAt ?: "",
                                    starMetric = it.stargazersCount.toString(),
                                    viewMetric = it.watchersCount.toString(),
                                    branchMetric = it.forksCount.toString(),
                                    visible = it.isExpanded,
                                    eventDescription = viewModel::eventExpandDescription,
                                    eventTransitionCompany = { viewModel.eventNavigateToWebView(url = it.htmlUrl) },
                                    eventTransitionRepository = {
                                        viewModel.eventNavigateToRepository(
                                            owner = it.ownerName,
                                            repositoryName = it.name
                                        )
                                    },
                                )

                            UserOrRepositorySelector.User -> UserCard(
                                userName = it.name ?: "",
                                image = it.avatarUrl ?: "",
                                eventTransition = { viewModel.eventNavigateToWebView(url = it.htmlUrl) })
                        }
                    }
                }
            }
        }
    }
}





