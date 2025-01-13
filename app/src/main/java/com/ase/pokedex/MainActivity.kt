package com.ase.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.ase.pokedex.ui.common.LoadingIndicator
import com.ase.pokedex.ui.navigation.Navigation
import com.ase.pokedex.ui.theme.PokeColor
import com.ase.pokedex.ui.theme.PokemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(PokeColor.toArgb()), navigationBarStyle = SystemBarStyle.dark(PokeColor.toArgb())
        )
        setContent {
            Navigation()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> Screen(
    state: Result<T>,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    topAppBar: @Composable (TopAppBarScrollBehavior) -> Unit,
    content: @Composable (T) -> Unit,
) {
    PokemonTheme {
        Scaffold(
            topBar = { topAppBar(scrollBehavior) },
            contentWindowInsets = WindowInsets.safeDrawing,
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
        ) { padding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                when (state) {
                    is Result.Loading -> {
                        LoadingIndicator()
                    }

                    is Result.Success -> {
                        content(state.data)
                    }

                    is Result.Error -> {
                        // Handle error
                    }
                }
            }
        }
    }
}
