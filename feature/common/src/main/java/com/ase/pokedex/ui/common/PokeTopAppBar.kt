package com.ase.pokedex.ui.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.ase.pokedex.ui.theme.PokeColor

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PokeTopAppBar(
    title: String,
    navigationIcon: @Composable () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
            )
        },
        scrollBehavior = scrollBehavior,
        navigationIcon = navigationIcon,
        colors = TopAppBarDefaults.topAppBarColors(
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            containerColor = PokeColor,
            scrolledContainerColor = PokeColor,
        )
    )
}
