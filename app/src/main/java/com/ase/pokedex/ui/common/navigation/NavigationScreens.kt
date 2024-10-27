package com.ase.pokedex.ui.common.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class PokemonDetail(val pokemonId: Int)
