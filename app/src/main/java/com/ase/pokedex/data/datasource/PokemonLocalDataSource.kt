package com.ase.pokedex.data.datasource

import com.ase.pokedex.domain.Pokemon
import kotlinx.coroutines.flow.Flow

interface LocalDataInterface {
    val pokemonList: Flow<List<Pokemon>>
    fun findPokemonById(id: Int): Flow<Pokemon?>
    suspend fun savePokemonList(pokemon: List<Pokemon>)
    suspend fun updatePokemon(pokemon: Pokemon)
    suspend fun isEmpty(): Boolean
    suspend fun clearDatabase()
}
