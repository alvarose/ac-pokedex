package com.ase.pokedex.domain.pokemon.usecases

import com.ase.pokedex.domain.pokemon.data.PokemonRepository
import com.ase.pokedex.domain.pokemon.models.Pokemon
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class FindPokemonByIdUseCase(
    private val repository: PokemonRepository,
) {
    operator fun invoke(id: Int): Flow<Pokemon> = repository.findPokemonById(id)
}
