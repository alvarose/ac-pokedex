package com.ase.pokedex.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ase.pokedex.data.PokemonRepository
import com.ase.pokedex.data.model.Pokemon
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailViewModel(
    id: Int,
    private val repository: PokemonRepository,
) : ViewModel() {

    val state = repository.findPokemonById(id)
        .map { pokemon -> UiState(pokemon = pokemon) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState(loading = true)
        )

    data class UiState(
        val loading: Boolean = false,
        val pokemon: Pokemon? = null,
    )

    fun onFavoriteClicked() {
        state.value.pokemon?.let { pokemon ->
            viewModelScope.launch {
                repository.toggleFavorite(pokemon)
            }
        }
    }
}
