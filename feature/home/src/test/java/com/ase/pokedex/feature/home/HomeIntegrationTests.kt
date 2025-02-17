package com.ase.pokedex.feature.home

import app.cash.turbine.test
import com.ase.pokedex.Result
import com.ase.pokedex.domain.pokemon.models.Pokemon
import com.ase.pokedex.domain.pokemon.usecases.FetchPokemonListUseCase
import com.ase.pokedex.test.data.buildPokemonRepositoryWith
import com.ase.pokedex.test.rules.CoroutinesTestRule
import com.ase.pokedex.test.samplePokemonList
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.collections.emptyList

class HomeIntegrationTests {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private fun buildViewModelWith(
        localData: List<Pokemon> = emptyList(),
        remoteData: List<Pokemon> = emptyList(),
    ) = HomeViewModel(FetchPokemonListUseCase(buildPokemonRepositoryWith(localData = localData, remoteData = remoteData)))

    @Test
    fun `Data is loaded from remote when local is empty`() = runTest() {
        // Given
        val remoteData = samplePokemonList(1, 2, 3)
        val viewModel = buildViewModelWith(remoteData = remoteData)

        // When
        viewModel.onUiReady()

        // Then
        viewModel.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(emptyList<List<Pokemon>>()), awaitItem())
            assertEquals(Result.Success(remoteData), awaitItem())
        }
    }

    @Test
    fun `Data is loaded from local when is not empty`() = runTest() {
        // Given
        val localData = samplePokemonList(1, 2, 3)
        val viewModel = buildViewModelWith(localData = localData)

        // When
        viewModel.onUiReady()

        // Then
        viewModel.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(localData), awaitItem())
        }
    }

}
