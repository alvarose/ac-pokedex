package com.ase.pokedex.domain.pokemon.usecases

import com.ase.pokedex.domain.pokemon.data.PokemonRepository
import com.ase.pokedex.domain.pokemon.models.Pokemon

class ToggleFavoriteUseCase(
    private val repository: PokemonRepository,
) {
    suspend operator fun invoke(pokemon: Pokemon) = repository.toggleFavorite(pokemon)
}
