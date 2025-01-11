package com.ase.pokedex.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ase.pokedex.Result
import com.ase.pokedex.common.ex.log
import com.ase.pokedex.domain.Pokemon
import com.ase.pokedex.stateAsResultIn
import com.ase.pokedex.usecases.FetchPokemonListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel(
    private val fetchPokemonUseCase: FetchPokemonListUseCase,
) : ViewModel() {

    private val uiReady = MutableStateFlow(false)

    val state: StateFlow<Result<List<Pokemon>>> = uiReady
        .filter { it }
        .flatMapLatest { fetchPokemonUseCase() }
        .stateAsResultIn(viewModelScope)

    init {
        onUiReady()
    }

    fun onUiReady() {
        uiReady.value = true
    }
}
