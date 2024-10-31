package com.ase.pokedex.data.datasource

import com.ase.pokedex.data.datasource.remote.ApiClient
import com.ase.pokedex.data.datasource.remote.PokeTypeResult
import com.ase.pokedex.data.datasource.remote.PokemonResource
import com.ase.pokedex.data.datasource.remote.PokemonResult
import com.ase.pokedex.data.model.PokeType
import com.ase.pokedex.data.model.Pokemon
import com.ase.pokedex.data.model.formatPokemonName

class PokemonRemoteDataSource {

    suspend fun fetchPokemon(): List<Pokemon> =
        ApiClient
            .instance
            .fetchPokemon()
            .results.map {
                it.toDomain()
            }

    suspend fun findPokemonById(id: Int): Pokemon =
        ApiClient
            .instance
            .fetchPokemonById(id)
            .toDomain()
}

private fun PokemonResource.toDomain(): Pokemon {
    val pokemonId = url.trimEnd('/').substringAfterLast('/').toInt()
    return Pokemon(
        id = pokemonId,
        name = name.formatPokemonName(),
    )
}

private fun PokemonResult.toDomain() = Pokemon(
    id = id,
    name = name.formatPokemonName(),
    types = types.map { it.type.toDomain() },
)

private fun PokeTypeResult.toDomain() = PokeType.fromName(name)