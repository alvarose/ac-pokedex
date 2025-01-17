package com.ase.pokedex.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ase.pokedex.Result
import com.ase.pokedex.domain.pokemon.models.Pokemon
import com.ase.pokedex.domain.pokemon.usecases.FetchPokemonListUseCase
import com.ase.pokedex.stateAsResultIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import org.koin.android.annotation.KoinViewModel

@OptIn(ExperimentalCoroutinesApi::class)
@KoinViewModel
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
