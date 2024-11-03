package com.ase.pokedex.data.datasource

import com.ase.pokedex.data.datasource.remote.PokeTypeResult
import com.ase.pokedex.data.datasource.remote.PokemonResource
import com.ase.pokedex.data.datasource.remote.PokemonResult
import com.ase.pokedex.data.datasource.remote.PokemonService
import com.ase.pokedex.domain.model.PokeType
import com.ase.pokedex.domain.model.Pokemon
import com.ase.pokedex.domain.model.formatPokemonName

interface RemoteDataSource {
    suspend fun fetchPokemonList(): List<Pokemon>
    suspend fun findPokemonById(id: Int): Pokemon
}

class PokemonRemoteDataSource(
    private val pokemonService: PokemonService,
) : RemoteDataSource {

    override suspend fun fetchPokemonList(): List<Pokemon> =
        pokemonService
            .fetchPokemonList()
            .results.map {
                it.toDomain()
            }

    override suspend fun findPokemonById(id: Int): Pokemon =
        pokemonService
            .fetchPokemonById(id)
            .toDomain()
}

private fun PokemonResource.toDomain(): Pokemon {
    val pokemonId = url.trimEnd('/').substringAfterLast('/').toInt()
    return Pokemon(
        id = pokemonId,
        name = name.formatPokemonName(),
        favorite = false
    )
}

private fun PokemonResult.toDomain() = Pokemon(
    id = id,
    name = name.formatPokemonName(),
    types = types.map { it.type.toDomain() },
    favorite = false
)

private fun PokeTypeResult.toDomain() = PokeType.fromName(name)
