package com.ase.pokedex.domain.pokemon.usecases

import com.ase.pokedex.domain.pokemon.samplePokemonList
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class FetchPokemonListUseCaseTest {

    @Test
    fun `Invoke calls repository`() {
        // Given
        val pokemonFlow = flowOf(samplePokemonList(1, 2, 3, 4))

        val useCase = FetchPokemonListUseCase(mock {
            on { pokemonList } doReturn pokemonFlow
        })

        // When
        val result = useCase()

        // Then
        assertEquals(pokemonFlow, result)
    }

}
