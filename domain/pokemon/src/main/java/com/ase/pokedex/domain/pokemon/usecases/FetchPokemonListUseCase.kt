package com.ase.pokedex.domain.pokemon.usecases

import com.ase.pokedex.domain.pokemon.data.PokemonRepository
import com.ase.pokedex.domain.pokemon.models.Pokemon
import kotlinx.coroutines.flow.Flow

class FetchPokemonListUseCase(
    private val repository: PokemonRepository,
) {
    operator fun invoke(): Flow<List<Pokemon>> = repository.pokemonList
}
