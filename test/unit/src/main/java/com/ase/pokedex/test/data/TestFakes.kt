package com.ase.pokedex.test.data

import com.ase.pokedex.domain.pokemon.data.PokemonRepository
import com.ase.pokedex.domain.pokemon.data.datasource.LocalDataSource
import com.ase.pokedex.domain.pokemon.data.datasource.RemoteDataSource
import com.ase.pokedex.domain.pokemon.models.Pokemon
import com.ase.pokedex.test.samplePokemonList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

fun buildPokemonRepositoryWith(
    localData: List<Pokemon> = emptyList(),
    remoteData: List<Pokemon> = emptyList(),
): PokemonRepository {

    val localDataSource = FakeLocalDataSource().apply { cachedData.value = localData }
    val remoteDataSource = FakeRemoteDataSource().apply { apiData = remoteData }

    return PokemonRepository(
        localDataSource = localDataSource,
        remoteDataSource = remoteDataSource
    )
}

class FakeLocalDataSource() : LocalDataSource {

    var cachedData = MutableStateFlow<List<Pokemon>>(emptyList())

    override val pokemonList: Flow<List<Pokemon>> = cachedData

    override fun findPokemonById(id: Int): Flow<Pokemon?> = cachedData.map { pokemon -> pokemon.firstOrNull { it.id == id } }

    override suspend fun savePokemonList(pokemon: List<Pokemon>) {
        cachedData.value = pokemon
    }

    override suspend fun updatePokemon(pokemon: Pokemon) {
        cachedData.value = cachedData.value.map {
            if (it.id == pokemon.id) pokemon else it
        }
    }

    override suspend fun isEmpty(): Boolean = cachedData.value.isEmpty()

    override suspend fun clearDatabase() {
        cachedData.value = emptyList()
    }
}

class FakeRemoteDataSource() : RemoteDataSource {

    var apiData: List<Pokemon> = samplePokemonList(1, 2, 3)

    override suspend fun fetchPokemonList(): List<Pokemon> = apiData

    override suspend fun findPokemonById(id: Int): Pokemon = apiData.first { it.id == id }
}
