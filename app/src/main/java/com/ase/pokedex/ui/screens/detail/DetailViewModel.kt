package com.ase.pokedex.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ase.pokedex.Result
import com.ase.pokedex.domain.model.Pokemon
import com.ase.pokedex.ifSuccess
import com.ase.pokedex.stateAsResultIn
import com.ase.pokedex.usecases.FindPokemonByIdUseCase
import com.ase.pokedex.usecases.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    id: Int,
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
