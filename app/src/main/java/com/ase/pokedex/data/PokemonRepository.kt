package com.ase.pokedex.data

import com.ase.pokedex.data.datasource.PokemonLocalDataSource
import com.ase.pokedex.data.datasource.PokemonRemoteDataSource
import com.ase.pokedex.data.model.Pokemon

class PokemonRepository(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val localDataSource: PokemonLocalDataSource,
) {
    suspend fun fetchPokemon(): List<Pokemon> {
        if (localDataSource.isEmpty()) {
            val pokemonList = remoteDataSource.fetchPokemon()
            localDataSource.savePokemon(pokemonList)
        }
        return localDataSource.fetchPokemon()
    }

    suspend fun fetchPokemonById(id: Int): Pokemon {
        val pokemon = localDataSource.findPokemonById(id)
        if (pokemon == null || !pokemon.hasDataComplete()) {
            val pokemon = remoteDataSource.findPokemonById(id)
            localDataSource.updatePokemon(pokemon)
        }
        return checkNotNull(localDataSource.findPokemonById(id))
    }
}
