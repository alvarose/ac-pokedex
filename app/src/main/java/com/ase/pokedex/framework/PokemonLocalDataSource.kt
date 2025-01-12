package com.ase.pokedex.framework

import com.ase.pokedex.domain.PokeType
import com.ase.pokedex.domain.Pokemon
import com.ase.pokedex.framework.database.PokemonDao
import com.ase.pokedex.framework.database.PokemonEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonLocalDataSource(private val pokemonDao: PokemonDao) : com.ase.pokedex.data.datasource.LocalDataSource {

    override val pokemonList = pokemonDao.fetchPokemonList().map { pokemonDb -> pokemonDb.map { it.toDomain() } }

    override fun findPokemonById(id: Int): Flow<Pokemon?> = pokemonDao.findPokemonById(id).map { it?.toDomain() }

    override suspend fun savePokemonList(pokemon: List<Pokemon>) = pokemonDao.savePokemon(pokemon.map { it.toEntity() })

    override suspend fun updatePokemon(pokemon: Pokemon) = pokemonDao.updatePokemon(pokemon.toEntity())

    override suspend fun isEmpty() = pokemonDao.countPokemon() == 0

    override suspend fun clearDatabase() = pokemonDao.clearDatabase()
}

private fun Pokemon.toEntity() = PokemonEntity(
    id = id,
    name = name.formatPokemonName(),
    types = types.map { it.name }.joinToString(separator = ","),
    favorite = favorite
)

private fun PokemonEntity.toDomain() = Pokemon(
    id = id,
    name = name,
    types = if (!types.isNullOrEmpty()) {
        types.split(",").map { PokeType.fromName(it) }
    } else {
        emptyList()
    },
    favorite = favorite
)
