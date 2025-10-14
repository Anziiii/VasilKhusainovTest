package com.example.vasilkhusainovtest.ui.searchScreen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.vasilkhusainovtest.R
import com.example.vasilkhusainovtest.ui.searchScreen.PanelSearchSelector
import com.example.vasilkhusainovtest.ui.theme.LocalCustomColorsPalette
import com.example.vasilkhusainovtest.ui.theme.VasilKhusainovTestTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Preview
@Composable
fun PreviewSearchTopBar() {
    VasilKhusainovTestTheme {
        SearchTopBar(
            panelSearchSelector = PanelSearchSelector.Closed,
            textValue = "test",
            onValueChange = fun(_: String) {},
            onSearchClicked = fun() {},
            onBackClicked = fun() {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    panelSearchSelector: PanelSearchSelector = PanelSearchSelector.Closed,
    textValue: String,
    onValueChange: (text: String) -> Unit,
    onSearchClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    val systemUiController = rememberSystemUiController()

    val statusBarColor = LocalCustomColorsPalette.current.statusBarColor

    LaunchedEffect(Unit) {
        systemUiController.setSystemBarsColor(
            color = statusBarColor
        )
    }

    TopAppBar(
        title = {

            when (panelSearchSelector) {
                is PanelSearchSelector.Closed -> {
                    Text(
                        stringResource(R.string.search),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }

                is PanelSearchSelector.Open -> {

                    SearchTextField(
                        text = textValue,
                        onValueChange = onValueChange,
                        eventClosePanel = onSearchClicked
                    )
                }

                else -> {}
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.title_back),
                    tint = Color.White
                )
            }
        },
        actions = {

            when (panelSearchSelector) {
                is PanelSearchSelector.Closed -> {
                    IconButton(
                        onClick = onSearchClicked
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = stringResource(R.string.search),
                            tint = Color.White
                        )
                    }
                }
                else -> {}
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LocalCustomColorsPalette.current.statusBarColor,
        )
    )
}
