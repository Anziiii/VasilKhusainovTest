package com.example.vasilkhusainovtest.ui.treeScreen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.unit.sp
import com.example.vasilkhusainovtest.R
import com.example.vasilkhusainovtest.ui.theme.LocalCustomColorsPalette
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreeTopBar(
    title: String?,
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
            Text(
                title ?: "",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.White
            )
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
        actions = { },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LocalCustomColorsPalette.current.statusBarColor,
        )
    )
}