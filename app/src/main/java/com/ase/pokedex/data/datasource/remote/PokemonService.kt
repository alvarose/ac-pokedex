package com.ase.pokedex.data.datasource.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon?limit=151")
    suspend fun fetchPokemon(): PokeResponse<List<PokemonResource>>

    @GET("pokemon/{id}")
    suspend fun fetchPokemonById(
        @Path("id") id: Int,
    ): PokemonResult
}
