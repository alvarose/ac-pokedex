package com.ase.pokedex.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ase.pokedex.data.model.Pokemon
import com.ase.pokedex.data.api.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = PokemonRepository()

    private val _state = MutableStateFlow(UiState())
    val state get() = _state.asStateFlow()

    init {
        onUiReady()
    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(loading = false, pokemon = repository.fetchHeroes())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val pokemon: List<Pokemon> = emptyList(),
    )
}
