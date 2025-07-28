package com.itgonca.citiesapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = LightSkyBlue,
    onPrimary = Color.Black,
    primaryContainer = LightSkyBlue,
    secondary = BananaYellow,
    onSecondary = Color.Black,
    background = ChineseBlack,
    onBackground = ChineseWhite,
    surface = EerieBlack,
    onSurface = ChineseWhite,
    error = FireOpal,
    onError = Color.Black

)

private val LightColorScheme = lightColorScheme(
    primary = FrenchBlue,
    onPrimary = Color.White,
    primaryContainer = FrenchBlue,
    secondary = MetallicYellow,
    onSecondary = Color.Black,
    background = GhostWhite,
    onBackground = EerieBlack,
    onSurface = EerieBlack,
    error = PersianRed,
    onError = Color.White
)

@Composable
fun CitiesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dimens: Dimens = CitiesAppTheme.dimens,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    CompositionLocalProvider(LocalDimens provides dimens) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}