package com.ase.pokedex.feature.detail

import app.cash.turbine.test
import com.ase.pokedex.Result
import com.ase.pokedex.domain.pokemon.usecases.FindPokemonByIdUseCase
import com.ase.pokedex.domain.pokemon.usecases.ToggleFavoriteUseCase
import com.ase.pokedex.test.data.buildPokemonRepositoryWith
import com.ase.pokedex.test.rules.CoroutinesTestRule
import com.ase.pokedex.test.samplePokemon
import com.ase.pokedex.test.samplePokemonList
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class DetailIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: DetailViewModel

    private val pokemon = samplePokemon(3)

    @Before
    fun setUp() {
        val pokemonRepository = buildPokemonRepositoryWith(localData = samplePokemonList(1, 2, 3))
        viewModel = DetailViewModel(3, FindPokemonByIdUseCase(pokemonRepository), ToggleFavoriteUseCase(pokemonRepository))
    }

    @Test
    fun `UI is updated with the pokemon on start`() = runTest {
        viewModel.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(pokemon), awaitItem())
        }
    }

    @Test
    fun `Favorite action update data in local source`() = runTest(coroutinesTestRule.testDispatcher) {
        viewModel.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(pokemon), awaitItem())

            viewModel.onFavoriteClicked()
            runCurrent()

            assertEquals(Result.Success(pokemon.copy(favorite = true)), awaitItem())
        }
    }
}
