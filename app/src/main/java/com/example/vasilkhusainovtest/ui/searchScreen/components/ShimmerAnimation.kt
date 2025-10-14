package com.example.vasilkhusainovtest.ui.searchScreen.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vasilkhusainovtest.ui.theme.LocalCustomColorsPalette
import com.example.vasilkhusainovtest.ui.theme.VasilKhusainovTestTheme
import kotlinx.coroutines.launch


@Preview
@Composable
fun ShimmerTextPreview() {
    VasilKhusainovTestTheme {
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

@Composable
fun ShimmerAnimation(
    modifier: Modifier = Modifier,
    iterations: Int = Int.MAX_VALUE,
    shimmerColors: List<Color>,
) {

    val coroutineScope = rememberCoroutineScope()
    val translateAnim = remember { Animatable(0f) }
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnim.value - 200f, translateAnim.value - 200f),
        end = Offset(translateAnim.value, translateAnim.value)
    )

    LaunchedEffect(key1 = iterations) {
        if (iterations != Int.MAX_VALUE) {
            repeat(iterations) {
                translateAnim.animateTo(
                    targetValue = 1000f,
                    animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
                )
                translateAnim.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = 0, easing = LinearEasing)
                )
            }
        } else {
            coroutineScope.launch {
                while (true) {
                    translateAnim.animateTo(
                        targetValue = 1000f,
                        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
                    )
                    translateAnim.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(durationMillis = 0, easing = LinearEasing)
                    )
                }
            }
        }
    }

    Box(
        modifier = modifier
            .background(brush)
    ) {
    }
}