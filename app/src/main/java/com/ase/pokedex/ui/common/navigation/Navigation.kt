package com.ase.pokedex.ui.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ase.pokedex.PokeApp
import com.ase.pokedex.data.PokemonRepository
import com.ase.pokedex.data.datasource.PokemonLocalDataSource
import com.ase.pokedex.data.datasource.PokemonRemoteDataSource
import com.ase.pokedex.ui.screens.detail.DetailScreen
import com.ase.pokedex.ui.screens.detail.DetailViewModel
import com.ase.pokedex.ui.screens.home.HomeScreen
import com.ase.pokedex.ui.screens.home.HomeViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val app = LocalContext.current.applicationContext as PokeApp

    val pokemonRepository = PokemonRepository(
        PokemonRemoteDataSource(),
        PokemonLocalDataSource(app.db.pokemonDao())
    )

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                viewModel { HomeViewModel(pokemonRepository) }
            ) { pokemon -> navController.navigate(PokemonDetail(pokemon.id)) }
        }
        composable<PokemonDetail> { backStackEntry ->
            val pokemonId = requireNotNull(backStackEntry.toRoute<PokemonDetail>().pokemonId)
            DetailScreen(
                viewModel { DetailViewModel(pokemonId, pokemonRepository) }
            ) {
                navController.popBackStack()
            }
        }
    }
}
