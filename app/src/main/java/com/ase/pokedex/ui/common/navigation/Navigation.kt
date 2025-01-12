package com.ase.pokedex.ui.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ase.pokedex.PokeApp
import com.ase.pokedex.framework.PokemonLocalDataSource
import com.ase.pokedex.framework.PokemonRemoteDataSource
import com.ase.pokedex.framework.remote.ApiClient
import com.ase.pokedex.ui.screens.detail.DetailScreen
import com.ase.pokedex.ui.screens.detail.DetailViewModel
import com.ase.pokedex.ui.screens.home.HomeScreen
import com.ase.pokedex.ui.screens.home.HomeViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val app = LocalContext.current.applicationContext as PokeApp

    val pokemonRepository = com.ase.pokedex.data.PokemonRepository(
        PokemonRemoteDataSource(ApiClient.instance),
        PokemonLocalDataSource(app.db.pokemonDao())
    )

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                viewModel { HomeViewModel(com.ase.pokedex.usecases.FetchPokemonListUseCase(pokemonRepository)) }
            ) { pokemon -> navController.navigate(PokemonDetail(pokemon.id)) }
        }
        composable<PokemonDetail> { backStackEntry ->
            val pokemonId = requireNotNull(backStackEntry.toRoute<PokemonDetail>().pokemonId)
            DetailScreen(
                viewModel {
                    DetailViewModel(
                        pokemonId,
                        com.ase.pokedex.usecases.FindPokemonByIdUseCase(pokemonRepository),
                        com.ase.pokedex.usecases.ToggleFavoriteUseCase(pokemonRepository)
                    )
                }
            ) {
                navController.popBackStack()
            }
        }
    }
}
