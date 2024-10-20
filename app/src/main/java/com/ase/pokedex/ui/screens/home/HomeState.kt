@file:OptIn(ExperimentalMaterial3Api::class)

package com.ase.pokedex.ui.screens.home

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class HomeState(
    val scrollBehavior: TopAppBarScrollBehavior,
    val lazyListState: LazyListState,
)

@Composable
fun rememberHomeState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    lazyListState: LazyListState = rememberLazyListState(),
): HomeState {
    return remember(scrollBehavior, lazyListState) { HomeState(scrollBehavior, lazyListState) }
}
