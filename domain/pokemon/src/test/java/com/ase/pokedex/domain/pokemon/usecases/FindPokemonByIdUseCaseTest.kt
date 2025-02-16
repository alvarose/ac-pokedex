package com.ase.pokedex.domain.pokemon.usecases

import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class FindPokemonByIdUseCaseTest {

    @Test
    fun `Invoke calls repository`() {
        // Given
        val pokemonFlow = flowOf(samplePokemon(1))

        val useCase = FindPokemonByIdUseCase(mock {
            on { findPokemonById(1) } doReturn pokemonFlow
        })

        // When
        val result = useCase(1)

        // Then
        assertEquals(pokemonFlow, result)
    }

}
