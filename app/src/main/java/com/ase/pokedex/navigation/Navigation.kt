package com.ase.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ase.pokedex.PokeApp
import com.ase.pokedex.domain.pokemon.data.PokemonRepository
import com.ase.pokedex.domain.pokemon.usecases.FetchPokemonListUseCase
import com.ase.pokedex.domain.pokemon.usecases.FindPokemonByIdUseCase
import com.ase.pokedex.domain.pokemon.usecases.ToggleFavoriteUseCase
import com.ase.pokedex.framework.pokemon.remote.ApiClient
import com.ase.pokedex.feature.detail.DetailScreen
import com.ase.pokedex.feature.detail.DetailViewModel
import com.ase.pokedex.feature.home.HomeScreen
import com.ase.pokedex.feature.home.HomeViewModel
import com.ase.pokedex.framework.pokemon.PokemonLocalDataSource
import com.ase.pokedex.framework.pokemon.PokemonRemoteDataSource

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val app = LocalContext.current.applicationContext as PokeApp

    val pokemonRepository = PokemonRepository(
        PokemonRemoteDataSource(ApiClient.instance),
        PokemonLocalDataSource(app.db.pokemonDao())
    )

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                viewModel { HomeViewModel(FetchPokemonListUseCase(pokemonRepository)) }
            ) { pokemon -> navController.navigate(PokemonDetail(pokemon.id)) }
        }
        composable<PokemonDetail> { backStackEntry ->
            val pokemonId = requireNotNull(backStackEntry.toRoute<PokemonDetail>().pokemonId)
            DetailScreen(
                viewModel {
                    DetailViewModel(
                        pokemonId,
                        FindPokemonByIdUseCase(pokemonRepository),
                        ToggleFavoriteUseCase(pokemonRepository)
                    )
                }
            ) {
                navController.popBackStack()
            }
        }
    }
}
