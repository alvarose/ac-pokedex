package com.ase.pokedex.domain.pokemon.data.datasource

import com.ase.pokedex.domain.pokemon.models.Pokemon
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    val pokemonList: Flow<List<Pokemon>>
    fun findPokemonById(id: Int): Flow<Pokemon?>
    suspend fun savePokemonList(pokemon: List<Pokemon>)
    suspend fun updatePokemon(pokemon: Pokemon)
    suspend fun isEmpty(): Boolean
    suspend fun clearDatabase()
}
