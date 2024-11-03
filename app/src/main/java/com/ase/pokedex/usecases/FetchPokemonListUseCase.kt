package com.ase.pokedex.usecases

import com.ase.pokedex.data.PokemonRepository
import com.ase.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

class FetchPokemonListUseCase(
    private val repository: PokemonRepository,
) {
    operator fun invoke(): Flow<List<Pokemon>> = repository.pokemonList
}
