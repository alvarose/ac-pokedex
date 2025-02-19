package com.ase.pokedex.domain.pokemon.usecases

import com.ase.pokedex.domain.pokemon.data.PokemonRepository
import com.ase.pokedex.domain.pokemon.models.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindPokemonByIdUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    operator fun invoke(id: Int): Flow<Pokemon> = repository.findPokemonById(id)
}
