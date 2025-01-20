package com.ase.pokedex

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ase.pokedex.feature.detail.DetailScreen
import com.ase.pokedex.feature.home.HomeScreen
import com.ase.pokedex.ui.navigation.Home
import com.ase.pokedex.ui.navigation.PokemonDetail

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen { pokemon -> navController.navigate(PokemonDetail(pokemon.id)) }
        }
        composable<PokemonDetail> { backStackEntry ->
            DetailScreen {
                navController.popBackStack()
            }
        }
    }
}
