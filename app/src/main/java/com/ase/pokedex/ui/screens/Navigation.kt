package com.ase.pokedex.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ase.pokedex.data.model.Pokemon
import com.ase.pokedex.ui.screens.detail.DetailScreen
import com.ase.pokedex.ui.screens.detail.DetailViewModel
import com.ase.pokedex.ui.screens.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen { pokemon -> navController.navigate("pokemon/${pokemon.id}") }
        }
        composable(
            "pokemon/{pokemonId}",
            arguments = listOf(navArgument(name = "pokemonId") { type = NavType.Companion.IntType })
        ) { backStackEntry ->
            val pokemonId = requireNotNull(backStackEntry.arguments?.getInt("pokemonId"))
            DetailScreen(
                viewModel { DetailViewModel(pokemonId) }
            ) {
                navController.popBackStack()
            }
        }
    }
}
