package com.ase.pokedex.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ase.pokedex.data.PokemonRepository
import com.ase.pokedex.data.model.Pokemon
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel(
    private val repository: PokemonRepository,
) : ViewModel() {

    private val uiReady = MutableStateFlow(false)

    val state = uiReady
        .filter { it }
        .flatMapLatest { repository.pokemonList }
        .map { list -> UiState(pokemonList = list) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState(loading = true)
        )

    init {
        onUiReady()
    }

    fun onUiReady() {
        uiReady.value = true
    }

    data class UiState(
        val loading: Boolean = false,
        val pokemonList: List<Pokemon> = emptyList(),
    )
}
