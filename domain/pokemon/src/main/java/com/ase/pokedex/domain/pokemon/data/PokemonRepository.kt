package com.ase.pokedex.domain.pokemon.data

import com.ase.pokedex.domain.pokemon.data.datasource.LocalDataSource
import com.ase.pokedex.domain.pokemon.data.datasource.RemoteDataSource
import com.ase.pokedex.domain.pokemon.models.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import org.koin.core.annotation.Factory
import kotlin.let

@Factory
class PokemonRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
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
