package com.ase.pokedex.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val PokemonColorScheme = lightColorScheme(
    primary = PokeColor,
    secondary = PokeColorDark,
    tertiary = PokeColor,

    background = PokeBackground,
    surface = PokeBackground,

//     Other default colors to override
//     onPrimary = Color.White,
//     onSecondary = Color.White,
//     onTertiary = Color.White,
//     onBackground = Color(0xFF1C1B1F),
//     onSurface = Color(0xFF1C1B1F),
)

@Composable
fun PokemonTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = PokemonColorScheme,
        typography = Typography,
        content = content
    )
}
