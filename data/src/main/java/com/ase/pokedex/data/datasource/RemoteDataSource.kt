package com.ase.pokedex.data.datasource

import com.ase.pokedex.domain.Pokemon

interface RemoteDataSource {
    suspend fun fetchPokemonList(): List<Pokemon>
    suspend fun findPokemonById(id: Int): Pokemon
}
