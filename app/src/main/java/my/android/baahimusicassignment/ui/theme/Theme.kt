package my.android.baahimusicassignment.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = DarkTextColor,
    onPrimary = DarkBackgroundColor,
    background = DarkBackgroundColor,
    surface = DarkCardColor,
    onSurface = DarkTextColor,
    primaryContainer = LightSecondaryBackground,
    secondary = DarkAccentColor,
    onSecondary = DarkSectionTitleColor
)

private val LightColorScheme = lightColorScheme(
    primary = LightTextColor,
    onPrimary = LightBackgroundColor,
    background = LightBackgroundColor,
    surface = LightCardColor,
    onSurface = LightTextColor,
    primaryContainer = LightSecondaryBackground,
    secondary = LightAccentColor,
    onSecondary = LightSectionTitleColor
)

@Composable
fun BaahiMusicAssignmentTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}