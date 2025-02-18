package com.ase.pokedex.feature.detail

import app.cash.turbine.test
import com.ase.pokedex.Result
import com.ase.pokedex.domain.pokemon.usecases.FindPokemonByIdUseCase
import com.ase.pokedex.domain.pokemon.usecases.ToggleFavoriteUseCase
import com.ase.pokedex.test.rules.CoroutinesTestRule
import com.ase.pokedex.test.samplePokemon
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var findPokemonByIdUseCase: FindPokemonByIdUseCase

    @Mock
    lateinit var toggleFavoriteUseCase: ToggleFavoriteUseCase

    private lateinit var viewModel: DetailViewModel

    private val pokemon = samplePokemon(1)

    @Before
    fun setUp() {
        whenever(findPokemonByIdUseCase(1)).thenReturn(flowOf(pokemon))
        viewModel = DetailViewModel(1, findPokemonByIdUseCase, toggleFavoriteUseCase)
    }

    @Test
    fun `UI is updated with the pokemon on start`() = runTest {
        viewModel.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(pokemon), awaitItem())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Favorite action calls the corresponding use case`() = runTest(coroutinesTestRule.testDispatcher) {
        viewModel.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(pokemon), awaitItem())

            viewModel.onFavoriteClicked()
            runCurrent()

            verify(toggleFavoriteUseCase).invoke(any())
        }
    }
}
