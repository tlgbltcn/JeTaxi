package com.tlgbltcn.jetaxi.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = OrangePrimary,
    primaryVariant = OrangePrimaryVariant,
    background = DarkBackground,
    surface = DarkBackground
)

private val LightColorPalette = lightColors(
    primary = PurplePrimary,
    primaryVariant = PurplePrimaryVariant,
    background = LightBackground,
    surface = LightBackground
)

@Composable
fun JeTaxiTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}