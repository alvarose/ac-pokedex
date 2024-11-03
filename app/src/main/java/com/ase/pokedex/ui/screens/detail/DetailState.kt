@file:OptIn(ExperimentalMaterial3Api::class)

package com.ase.pokedex.ui.screens.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.ase.pokedex.Result
import com.ase.pokedex.domain.model.Pokemon

class DetailState(
    private val state: Result<Pokemon>,
    val scrollBehavior: TopAppBarScrollBehavior,
) {
    val pokemon: Pokemon?
        get() = (state as? Result.Success)?.data
}

@Composable
fun rememberDetailState(
    state: Result<Pokemon>,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
): DetailState {
    return remember(scrollBehavior) { DetailState(state, scrollBehavior) }
}
