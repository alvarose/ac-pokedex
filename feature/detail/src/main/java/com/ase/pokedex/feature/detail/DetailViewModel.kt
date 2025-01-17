package com.ase.pokedex.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ase.pokedex.Result
import com.ase.pokedex.domain.pokemon.models.Pokemon
import com.ase.pokedex.domain.pokemon.usecases.FindPokemonByIdUseCase
import com.ase.pokedex.domain.pokemon.usecases.ToggleFavoriteUseCase
import com.ase.pokedex.ifSuccess
import com.ase.pokedex.stateAsResultIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailViewModel @Inject constructor(
    @Named("pokemonId") val id: Int,
    private val findPokemonByIdUseCase: FindPokemonByIdUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
) : ViewModel() {

    val state: StateFlow<Result<Pokemon>> = findPokemonByIdUseCase(id)
        .stateAsResultIn(viewModelScope)

    fun onFavoriteClicked() {
        state.value.ifSuccess { pokemon ->
            viewModelScope.launch {
                toggleFavoriteUseCase(pokemon)
            }
        }
    }
}
