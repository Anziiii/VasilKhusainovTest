package com.example.vasilkhusainovtest.ui.searchScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vasilkhusainovtest.R
import com.example.vasilkhusainovtest.ui.theme.LocalCustomColorsPalette


@Composable
fun Widget(
    innerPadding: PaddingValues,
    titleText: Int,
    descriptionText: Int,
    animationResource: Int,
    isEventSupport: Boolean = false,
    titleEvent: Int? = null,
    event: () -> Unit = fun() {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PaintAnimation(animationResource)
        Text(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            text = stringResource(titleText),
            fontWeight = FontWeight.Bold,
            color = LocalCustomColorsPalette.current.primaryTextColor
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            text = stringResource(descriptionText),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = LocalCustomColorsPalette.current.secondaryTextColor
        )

        if (isEventSupport) {
            Button(
                modifier = Modifier.padding(top = 32.dp),
                onClick = event,
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LocalCustomColorsPalette.current.buttonColor)
            ) {
                Text(
                    text = stringResource(titleEvent ?: R.string.error_content_title),
                    color = LocalCustomColorsPalette.current.primaryTextColor
                )
            }
        }
    }
}