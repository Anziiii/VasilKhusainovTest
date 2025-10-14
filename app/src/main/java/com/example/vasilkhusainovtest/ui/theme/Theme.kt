package com.example.vasilkhusainovtest.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Immutable
data class ExtendedColorScheme(
    val primaryTextColor: Color = Color.Unspecified,
    val secondaryTextColor: Color = Color.Unspecified,
    val backgroundColor: Color = Color.Unspecified,
    val statusBarColor: Color = Color.Unspecified,
    val textFieldColor: TextFieldScheme = TextFieldScheme(),
    val iconGrayColor: Color = Color.Unspecified,
    val shimmerEffectColor: ShimmerEffect = ShimmerEffect(),
    val hierarchyThree: HierarchyThree = HierarchyThree(),
    val customCard: CustomCard = CustomCard(),
    val buttonColor: Color = Color.Unspecified
)

@Immutable
data class TextFieldScheme(
    val cursorColor: Color = Color.Unspecified,
    val focusedBorderColor: Color = Color.Unspecified,
    val unfocusedBorderColor: Color = Color.Unspecified,
    val focusedContainerColor: Color = Color.Unspecified,
    val unfocusedContainerColor: Color = Color.Unspecified,
    val focusedLeadingIconColor: Color = Color.Unspecified,
    val unfocusedLeadingIconColor: Color = Color.Unspecified,
)

@Immutable
data class HierarchyThree(
    val folderColor: Color = Color.Unspecified,
    val fileColor: Color = Color.Unspecified,
    val dividerColor: Color = Color.Unspecified,
)

@Immutable
data class CustomCard(
    val containerColor: Color = Color.Unspecified,
    val secondContainerColor: Color = Color.Unspecified,
    val iconColor: Color = Color.Unspecified,
    val iconSurfaceColor: Color = Color.Unspecified,
)

@Immutable
data class ShimmerEffect(
    val primaryGradientColor: Color = Color.Unspecified,
    val secondaryGradientColor: Color = Color.Unspecified,
    val shimmerColor: Color = Color.Unspecified
)

val extendedLight = ExtendedColorScheme(
    primaryTextColor = PrimaryTextColorLight,
    secondaryTextColor = SecondaryTextColorLight,
    backgroundColor = BackgroundColorLight,
    statusBarColor = StatusBarColorLight,
    textFieldColor = TextFieldScheme(
        cursorColor = cursorColorLight,
        focusedBorderColor = focusedBorderColorLight,
        unfocusedBorderColor = unfocusedBorderColorLight,
        focusedContainerColor = focusedContainerColorLight,
        unfocusedContainerColor = unfocusedContainerColorLight,
        focusedLeadingIconColor = focusedLeadingIconColorLight,
        unfocusedLeadingIconColor = unfocusedLeadingIconColorLight,
    ),
    iconGrayColor = iconGrayLight,
    shimmerEffectColor = ShimmerEffect(
        primaryGradientColor = primaryGradientColorLight,
        secondaryGradientColor = secondaryGradientColorLight,
        shimmerColor = shimmerColorLight
    ),
    hierarchyThree = HierarchyThree(
        folderColor = folderColorLight,
        fileColor = fileColorLight,
        dividerColor = dividerColorLight
    ),
    customCard = CustomCard(
        containerColor = containerCardColorLight,
        secondContainerColor = secondCardContainerColorLight,
        iconColor = iconCardColorLight,
        iconSurfaceColor = iconSurfaceCardColorLight,
    ),
    buttonColor = buttonColorLight
)

val extendedDark = ExtendedColorScheme(
    primaryTextColor = PrimaryTextColorDark,
    secondaryTextColor = SecondaryTextColorDark,
    backgroundColor = BackgroundColorDark,
    statusBarColor = StatusBarColorDark,
    textFieldColor = TextFieldScheme(
        cursorColor = cursorColorDark,
        focusedBorderColor = focusedBorderColorDark,
        unfocusedBorderColor = unfocusedBorderColorDark,
        focusedContainerColor = focusedContainerColorDark,
        unfocusedContainerColor = unfocusedContainerColorDark,
        focusedLeadingIconColor = focusedLeadingIconColorDark,
        unfocusedLeadingIconColor = unfocusedLeadingIconColorDark,
    ),
    iconGrayColor = iconGrayDark,
    shimmerEffectColor = ShimmerEffect(
        primaryGradientColor = primaryGradientColorDark,
        secondaryGradientColor = secondaryGradientColorDark,
        shimmerColor = shimmerColorDark
    ),
    hierarchyThree = HierarchyThree(
        folderColor = folderColorDark,
        fileColor = fileColorDark,
        dividerColor = dividerColorDark
    ),
    customCard = CustomCard(
        containerColor = containerCardColorDark,
        secondContainerColor = secondCardContainerColorDark,
        iconColor = iconCardColorDark,
        iconSurfaceColor = iconSurfaceCardColorDark,
    ),
    buttonColor = buttonColorDark
)

val LocalCustomColorsPalette = staticCompositionLocalOf { ExtendedColorScheme() }

@Composable
fun VasilKhusainovTestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val customColorsPalette =
        if (darkTheme) extendedDark
        else extendedLight

    CompositionLocalProvider(
        LocalCustomColorsPalette provides customColorsPalette // our custom palette
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}