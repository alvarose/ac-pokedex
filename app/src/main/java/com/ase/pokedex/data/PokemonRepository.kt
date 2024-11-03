package com.ase.pokedex.data

import com.ase.pokedex.data.datasource.PokemonLocalDataSource
import com.ase.pokedex.data.datasource.PokemonRemoteDataSource
import com.ase.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach

class PokemonRepository(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val localDataSource: PokemonLocalDataSource,
) {
    val pokemonList: Flow<List<Pokemon>> = localDataSource.pokemonList
        .onEach { localData ->
            if (localData.isEmpty()) {
                val remoteData = remoteDataSource.fetchPokemonList()
                localDataSource.savePokemonList(remoteData)
            }
        }

    fun findPokemonById(id: Int): Flow<Pokemon> = localDataSource.findPokemonById(id)
        .onEach { localPokemon ->
            localPokemon?.let {
                if (!it.hasDataComplete()) {
                    val remotePokemon = remoteDataSource.findPokemonById(id)
                    localDataSource.updatePokemon(remotePokemon)
                }
            }
        }
        .filterNotNull()

    suspend fun toggleFavorite(pokemon: Pokemon) = localDataSource.updatePokemon(pokemon.copy(favorite = !pokemon.favorite))
}
