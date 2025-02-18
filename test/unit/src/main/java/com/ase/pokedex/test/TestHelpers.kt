package com.ase.pokedex.test

import com.ase.pokedex.domain.pokemon.models.PokeType
import com.ase.pokedex.domain.pokemon.models.Pokemon
import kotlin.Int

fun samplePokemon(id: Int): Pokemon = Pokemon(
    id = id,
    name = "Pokemon $id",
    types = listOf(PokeType.NORMAL),
    favorite = false,
    avatar = "",
    image = ""
)

fun samplePokemonList(vararg ids: Int): List<Pokemon> = ids.map { samplePokemon(it) }
