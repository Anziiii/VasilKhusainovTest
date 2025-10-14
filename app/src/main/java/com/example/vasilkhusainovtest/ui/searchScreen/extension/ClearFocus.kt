package com.example.vasilkhusainovtest.ui.searchScreen.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalFocusManager


fun Modifier.clearFocusOnTap(): Modifier = composed {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    clickable(
        interactionSource = interactionSource,
        indication = null
    ) {
        focusManager.clearFocus()
    }
}


fun Modifier.clearFocusOnScrollAndTap(lazyListState: LazyListState): Modifier = composed {
    val focusManager = LocalFocusManager.current

    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (lazyListState.isScrollInProgress) {
            focusManager.clearFocus()
        }
    }

    val interactionSource = remember { MutableInteractionSource() }

    this
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            focusManager.clearFocus()
        }
}