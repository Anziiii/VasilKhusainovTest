package com.example.vasilkhusainovtest.ui.treeScreen

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.example.vasilkhusainovtest.R
import com.example.vasilkhusainovtest.ui.searchScreen.components.ShimmerAnimation
import com.example.vasilkhusainovtest.ui.searchScreen.components.Widget
import com.example.vasilkhusainovtest.ui.theme.LocalCustomColorsPalette
import com.example.vasilkhusainovtest.ui.treeScreen.components.TreeHierarchyItem
import com.example.vasilkhusainovtest.ui.treeScreen.components.TreeTopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun TreeScreen(
    navController: NavController,
    viewModel: TreeScreenViewModel = koinViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    val lifecycleOwner = LocalLifecycleOwner.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TreeTopBar(
                title = state.titleHeader,
                onBackClicked = viewModel::eventNavigateToBack
            )
        },
        containerColor = LocalCustomColorsPalette.current.backgroundColor
    ) { innerPadding ->

        LaunchedEffect(viewModel.effect, lifecycleOwner.lifecycle) {

            lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect { event ->
                    when (event) {
                        Effect.NavigateBack -> {
                            navController.popBackStack()
                        }

                        is Effect.NavigateToFile -> {
                            val encodedUrl = Uri.encode(event.url)
                            navController.navigate("webViewScreen/$encodedUrl")
                        }

                        is Effect.NavigateToFolder -> {
                            val encodedPath = Uri.encode(event.path)

                            val route =
                                "repository/${event.ownerName}/${event.repositoryName}?path=$encodedPath"

                            navController.navigate(route)
                        }
                    }
                }
            }
        }

        when (state.contentStatus) {
            ContentStatus.EmptyContent -> {

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

            ContentStatus.Loading ->
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
                                .height(24.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            shimmerColors = listOf(
                                LocalCustomColorsPalette.current.shimmerEffectColor.primaryGradientColor,
                                LocalCustomColorsPalette.current.shimmerEffectColor.shimmerColor,
                                LocalCustomColorsPalette.current.shimmerEffectColor.secondaryGradientColor
                            )
                        )
                    }
                }

            ContentStatus.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(start = 6.dp, end = 6.dp)
                ) {
                    items(state.items) {
                        TreeHierarchyItem(
                            type = it.typeObject,
                            titleName = it.name,
                            titleSize = it.size.toString(),
                            eventDirectory = {
                                viewModel.eventNavigateToFolder(
                                    path = it.path
                                )
                            },
                            eventFile = { viewModel.eventNavigateToFile(it.htmlUrl) }
                        )
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 1.dp,
                            color = LocalCustomColorsPalette.current.hierarchyThree.dividerColor
                        )
                    }
                }
            }
        }
    }
}