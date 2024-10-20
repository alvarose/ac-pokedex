package com.ase.pokedex.data.api

import kotlinx.serialization.Serializable

@Serializable
data class PokeResponse<T>(
    val results: T,
)

@Serializable
data class PokemonResource(
    val name: String,
    val url: String,
)

@Serializable
data class PokemonResult(
    val id: Int,
    val name: String,
    val types: List<PokeTypeResource>
)

@Serializable
data class PokeTypeResource(
    val type: PokeTypeResult,
)

@Serializable
data class PokeTypeResult(
    val name: String,
    val url: String,
)
