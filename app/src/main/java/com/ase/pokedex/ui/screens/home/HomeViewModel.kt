package com.ase.pokedex.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ase.pokedex.data.PokemonRepository
import com.ase.pokedex.data.model.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PokemonRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state get() = _state.asStateFlow()

    init {
        onUiReady()
    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            repository.pokemonList.collect { list ->
                _state.value = UiState(loading = false, pokemonList = list)
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val pokemonList: List<Pokemon> = emptyList(),
    )
}
