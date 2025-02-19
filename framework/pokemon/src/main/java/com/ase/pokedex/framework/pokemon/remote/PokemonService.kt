package com.ase.pokedex.framework.pokemon.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon?limit=151")
    suspend fun fetchPokemonList(): PokeResponse<List<PokemonResource>>

    @GET("pokemon/{id}")
    suspend fun fetchPokemonById(@Path("id") id: Int): PokemonResult
}
