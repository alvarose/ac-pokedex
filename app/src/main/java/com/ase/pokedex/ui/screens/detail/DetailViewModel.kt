package com.ase.pokedex.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ase.pokedex.domain.Pokemon
import com.ase.pokedex.Result
import com.ase.pokedex.ifSuccess
import com.ase.pokedex.stateAsResultIn
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    id: Int,
    private val findPokemonByIdUseCase: com.ase.pokedex.usecases.FindPokemonByIdUseCase,
    private val toggleFavoriteUseCase: com.ase.pokedex.usecases.ToggleFavoriteUseCase,
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
