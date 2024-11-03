package com.ase.pokedex.data.datasource

import com.ase.pokedex.data.datasource.database.PokemonDao
import com.ase.pokedex.data.datasource.database.PokemonEntity
import com.ase.pokedex.domain.model.PokeType
import com.ase.pokedex.domain.model.Pokemon
import com.ase.pokedex.domain.model.formatPokemonName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface LocalDataInterface {
    val pokemonList: Flow<List<Pokemon>>
    fun findPokemonById(id: Int): Flow<Pokemon?>
    suspend fun savePokemonList(pokemon: List<Pokemon>)
    suspend fun updatePokemon(pokemon: Pokemon)
    suspend fun isEmpty(): Boolean
    suspend fun clearDatabase()
}

class PokemonLocalDataSource(private val pokemonDao: PokemonDao) : LocalDataInterface {

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
