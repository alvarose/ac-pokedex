package com.ase.pokedex.usecases

import com.ase.pokedex.data.PokemonRepository
import com.ase.pokedex.domain.Pokemon

class ToggleFavoriteUseCase(private val repository: PokemonRepository) {
    suspend operator fun invoke(pokemon: Pokemon) = repository.toggleFavorite(pokemon)
}
