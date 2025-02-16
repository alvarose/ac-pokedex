package com.ase.pokedex.domain.pokemon.usecases

import com.ase.pokedex.domain.pokemon.models.PokeType
import com.ase.pokedex.domain.pokemon.models.Pokemon
import kotlin.Int

fun samplePokemon(id: Int): Pokemon = Pokemon(
    id = id,
    name = "Pokemon $id",
    types = List((1..2).random()) { PokeType.entries.random() },
    favorite = false
)

fun samplePokemonList(vararg ids: Int): List<Pokemon> = ids.map { samplePokemon(it) }
