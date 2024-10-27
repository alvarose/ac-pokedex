package com.ase.pokedex.ui.common.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ase.pokedex.ui.screens.detail.DetailScreen
import com.ase.pokedex.ui.screens.detail.DetailViewModel
import com.ase.pokedex.ui.screens.home.HomeScreen

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
            val pokemonId = requireNotNull(backStackEntry.toRoute<PokemonDetail>().pokemonId)
            DetailScreen(
                viewModel { DetailViewModel(pokemonId) }
            ) {
                navController.popBackStack()
            }
        }
    }
}
