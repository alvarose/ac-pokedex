@file:OptIn(ExperimentalMaterial3Api::class)

package com.ase.pokedex.ui.screens.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.ase.pokedex.common.toast

class DetailState(
    val scrollBehavior: TopAppBarScrollBehavior,
) {
    @Composable
    fun ShowMessageEffect(message: String?, onMessageShown: () -> Unit) {
        val context = LocalContext.current

        LaunchedEffect(message) {
            message?.toast(context)
            onMessageShown()
        }
    }
}

@Composable
fun rememberDetailState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
): DetailState {
    return remember(scrollBehavior) { DetailState(scrollBehavior) }
}
