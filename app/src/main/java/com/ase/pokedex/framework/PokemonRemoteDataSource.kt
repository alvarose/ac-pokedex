package com.ase.pokedex.framework

import com.ase.pokedex.domain.PokeType
import com.ase.pokedex.domain.Pokemon
import com.ase.pokedex.framework.remote.PokeTypeResult
import com.ase.pokedex.framework.remote.PokemonResource
import com.ase.pokedex.framework.remote.PokemonResult
import com.ase.pokedex.framework.remote.PokemonService


class PokemonRemoteDataSource(
    private val pokemonService: PokemonService,
) : com.ase.pokedex.data.datasource.RemoteDataSource {

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

fun String.formatPokemonName(): String {
    return this.replaceFirstChar(Char::titlecase)
        .replace("-f", " ♀")
        .replace("-m", " ♂")
}
