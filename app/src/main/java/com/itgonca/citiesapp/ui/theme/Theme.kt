package com.itgonca.citiesapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = LightSkyBlue,
    onPrimary = Color.Black,
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

    CompositionLocalProvider(LocalDimens provides dimens) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}