package com.ase.pokedex.feature.home

import app.cash.turbine.test
import com.ase.pokedex.Result
import com.ase.pokedex.domain.pokemon.usecases.FetchPokemonListUseCase
import com.ase.pokedex.test.rules.CoroutinesTestRule
import com.ase.pokedex.test.samplePokemonList
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var fetchPokemonListUseCase: FetchPokemonListUseCase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel(fetchPokemonListUseCase)
    }

    @Test
    fun `Pokemon list are not requested if UI is not ready`() = runTest {
        // Given
        viewModel.state.first()

        // When
        runCurrent()

        // Then
        verify(fetchPokemonListUseCase, times(0)).invoke()
    }

    @Test
    fun `Pokemon list are requested if UI is ready`() = runTest {
        // Given
        val pokemonList = samplePokemonList(1, 2, 3)
        whenever(fetchPokemonListUseCase()).thenReturn(flowOf(pokemonList))

        // When
        viewModel.onUiReady()

        // Then
        viewModel.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(pokemonList), awaitItem())
        }
    }

    @Test
    fun `Error received when requesting pokemon list failed`() = runTest {
        // Given
        val errorMessage = "PokeError!"
        whenever(fetchPokemonListUseCase()).thenThrow(RuntimeException(errorMessage))

        // When
        viewModel.onUiReady()

        // Then
        viewModel.state.test {
            assertEquals(Result.Loading, awaitItem())
            val exception = (awaitItem() as Result.Error).throwable.message
            assertEquals(errorMessage, exception)
        }
    }
}
