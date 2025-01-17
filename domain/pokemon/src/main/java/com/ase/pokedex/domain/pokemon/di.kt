package com.ase.pokedex.domain.pokemon

import com.ase.pokedex.domain.pokemon.data.PokemonRepository
import com.ase.pokedex.domain.pokemon.usecases.FetchPokemonListUseCase
import com.ase.pokedex.domain.pokemon.usecases.FindPokemonByIdUseCase
import com.ase.pokedex.domain.pokemon.usecases.ToggleFavoriteUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val pokemonDomainModule = module {
    factoryOf(::PokemonRepository)
    factoryOf(::FetchPokemonListUseCase)
    factoryOf(::FindPokemonByIdUseCase)
    factoryOf(::ToggleFavoriteUseCase)
}
