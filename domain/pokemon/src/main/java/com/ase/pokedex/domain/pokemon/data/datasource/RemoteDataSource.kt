package com.ase.pokedex.domain.pokemon.data.datasource

import com.ase.pokedex.domain.pokemon.models.Pokemon

interface RemoteDataSource {
    suspend fun fetchPokemonList(): List<Pokemon>
    suspend fun findPokemonById(id: Int): Pokemon
}
