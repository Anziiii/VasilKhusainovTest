package com.example.vasilkhusainovtest.ui.searchScreen.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vasilkhusainovtest.R
import com.example.vasilkhusainovtest.ui.theme.LocalCustomColorsPalette
import com.example.vasilkhusainovtest.ui.theme.VasilKhusainovTestTheme

@SuppressLint("RememberInComposition")
@Preview(showBackground = true)
@Composable
fun DemoYellowSearchField() {
    VasilKhusainovTestTheme {

        SearchTextField("abc", fun(_: String) {}, fun() {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    text: String,
    onValueChange: (String) -> Unit,
    eventClosePanel: () -> Unit
) {

    val focusManager = LocalFocusManager.current

    val focusRequester = remember { FocusRequester() }


    LaunchedEffect(Unit) {
        if (text.isEmpty()) {
            focusRequester.requestFocus()
        }
    }

    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        shape = RoundedCornerShape(8.dp),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            },
            onSend = {
                focusManager.clearFocus()
            },
            onPrevious = {
                focusManager.clearFocus()
            }
        ),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = stringResource(R.string.search))
        },

        singleLine = true,
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = LocalCustomColorsPalette.current.primaryTextColor,
        ),
        trailingIcon = {

            Icon(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(
                        LocalCustomColorsPalette.current.iconGrayColor
                    )
                    .clickable { eventClosePanel() },
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(R.string.title_back),
                tint = Color.White
            )
        },
        label = null,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = LocalCustomColorsPalette.current.textFieldColor.cursorColor,

            focusedBorderColor = LocalCustomColorsPalette.current.textFieldColor.focusedBorderColor,
            unfocusedBorderColor = LocalCustomColorsPalette.current.textFieldColor.unfocusedBorderColor,

            focusedTextColor = LocalCustomColorsPalette.current.primaryTextColor,
            unfocusedTextColor = LocalCustomColorsPalette.current.secondaryTextColor,

            focusedContainerColor = LocalCustomColorsPalette.current.textFieldColor.focusedContainerColor,
            unfocusedContainerColor = LocalCustomColorsPalette.current.textFieldColor.unfocusedContainerColor,

            focusedLeadingIconColor = LocalCustomColorsPalette.current.textFieldColor.focusedLeadingIconColor,
            unfocusedLeadingIconColor = LocalCustomColorsPalette.current.textFieldColor.unfocusedLeadingIconColor,
        ),
    )
}
