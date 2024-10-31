package com.ase.pokedex.data

import com.ase.pokedex.data.datasource.PokemonLocalDataSource
import com.ase.pokedex.data.datasource.PokemonRemoteDataSource
import com.ase.pokedex.data.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class PokemonRepository(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val localDataSource: PokemonLocalDataSource,
) {
    val pokemonList: Flow<List<Pokemon>> = localDataSource.pokemonList.transform { pokemonListDb ->
        val list = pokemonListDb.takeIf { it.isNotEmpty() }
            ?: remoteDataSource.fetchPokemonList().also {
                localDataSource.savePokemonList(it)
            }
        emit(list)
    }

    suspend fun findPokemonById(id: Int): Flow<Pokemon?> = localDataSource.findPokemonById(id).transform { pokemonDb ->
        val pokemon = pokemonDb?.takeIf { it.hasDataComplete() } ?: remoteDataSource.findPokemonById(id).also {
            localDataSource.updatePokemon(it)
        }
        emit(pokemon)
    }
}
