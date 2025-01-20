package com.ase.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import com.ase.pokedex.navigation.Navigation
import com.ase.pokedex.ui.theme.PokeColor

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
