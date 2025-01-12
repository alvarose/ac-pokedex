package com.ase.pokedex.usecases

import com.ase.pokedex.domain.Pokemon
import kotlinx.coroutines.flow.Flow

class FindPokemonByIdUseCase(
    private val repository: com.ase.pokedex.data.PokemonRepository,
) {
    operator fun invoke(id: Int): Flow<Pokemon> = repository.findPokemonById(id)
}
