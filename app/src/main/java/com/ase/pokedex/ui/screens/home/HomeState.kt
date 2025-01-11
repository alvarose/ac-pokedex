@file:OptIn(ExperimentalMaterial3Api::class)

package com.ase.pokedex.ui.screens.home

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class HomeState(
    val scrollBehavior: TopAppBarScrollBehavior,
    val lazyGridState: LazyGridState,
)

@Composable
fun rememberHomeState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    lazyGridState: LazyGridState = rememberLazyGridState(),
): HomeState {
    return remember(scrollBehavior, lazyGridState) { HomeState(scrollBehavior, lazyGridState) }
}
