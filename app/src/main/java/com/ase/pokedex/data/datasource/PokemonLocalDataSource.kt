package com.ase.pokedex.data.datasource

import com.ase.pokedex.data.datasource.database.PokemonDao
import com.ase.pokedex.data.datasource.database.PokemonEntity
import com.ase.pokedex.data.model.PokeType
import com.ase.pokedex.data.model.Pokemon
import com.ase.pokedex.data.model.formatPokemonName
import kotlinx.coroutines.flow.map

class PokemonLocalDataSource(private val pokemonDao: PokemonDao) {

    val pokemonList = pokemonDao.fetchPokemonList().map { pokemonDb -> pokemonDb.map { it.toDomain() } }

    fun findPokemonById(id: Int) = pokemonDao.findPokemonById(id).map { it?.toDomain() }

    suspend fun savePokemonList(pokemon: List<Pokemon>) = pokemonDao.savePokemon(pokemon.map { it.toEntity() })

    suspend fun updatePokemon(pokemon: Pokemon) = pokemonDao.updatePokemon(pokemon.toEntity())

    suspend fun isEmpty() = pokemonDao.countPokemon() == 0

    suspend fun clearDatabase() = pokemonDao.clearDatabase()
}

private fun Pokemon.toEntity() = PokemonEntity(
    id = id,
    name = name.formatPokemonName(),
    types = types.map { it.name }.joinToString(separator = ","),
)

private fun PokemonEntity.toDomain() = Pokemon(
    id = id,
    name = name,
    types = if (!types.isNullOrEmpty()) {
        types.split(",").map { PokeType.fromName(it) }
    } else {
        emptyList()
    },
)
