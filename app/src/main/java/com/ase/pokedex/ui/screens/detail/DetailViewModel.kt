package com.ase.pokedex.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ase.pokedex.data.model.Pokemon
import com.ase.pokedex.data.PokemonRepository
import com.ase.pokedex.data.datasource.PokemonRemoteDataSource
import com.ase.pokedex.ui.screens.home.HomeViewModel.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val id: Int,
    private val repository: PokemonRepository
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            repository.findPokemonById(id).collect { pokemon ->
                _state.value = UiState(loading = false, pokemon = pokemon)
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val pokemon: Pokemon? = null,
        val message: String? = null,
    )

    fun showMessage(message: String) {
        _state.update { it.copy(message = message) }
    }

    fun onMessageShown() {
        _state.update { it.copy(message = null) }
    }
}
