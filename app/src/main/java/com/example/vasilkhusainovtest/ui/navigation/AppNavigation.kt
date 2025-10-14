package com.example.vasilkhusainovtest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vasilkhusainovtest.ui.theme.SearchScreen
import com.example.vasilkhusainovtest.ui.treeScreen.TreeScreen
import com.example.vasilkhusainovtest.ui.treeScreen.TreeScreenViewModel
import com.example.vasilkhusainovtest.ui.webViewScreen.WebViewScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            SearchScreen(
                navController = navController
            )
        }

        composable(
            "webViewScreen/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: "about:blank"
            WebViewScreen(url = url)
        }

        composable(
            "repository/{owner}/{repositoryName}?path={path}",
            arguments = listOf(
                navArgument("owner") { type = NavType.StringType },
                navArgument("repositoryName") { type = NavType.StringType },
                navArgument("path") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = ""
                },
            )
        ) { backStackEntry ->
            val ownerName = backStackEntry.arguments?.getString("owner")
            val repositoryName = backStackEntry.arguments?.getString("repositoryName")
            val path = backStackEntry.arguments?.getString("path")

            val viewModel: TreeScreenViewModel = koinViewModel(
                parameters = { parametersOf(ownerName, repositoryName, path) }
            )

            TreeScreen(
                navController = navController,
                viewModel = viewModel,
            )
        }
    }
}
