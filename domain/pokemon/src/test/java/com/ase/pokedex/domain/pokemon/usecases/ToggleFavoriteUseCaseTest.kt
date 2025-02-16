package com.ase.pokedex.domain.pokemon.usecases

import com.ase.pokedex.domain.pokemon.data.PokemonRepository
import com.ase.pokedex.domain.pokemon.samplePokemon
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class ToggleFavoriteUseCaseTest {

    @Test
    fun `Invoke calls repository`(): Unit = runBlocking {
        // Given
        val pokemon = samplePokemon(1)
        val repository = mock<PokemonRepository>()
        val useCase = ToggleFavoriteUseCase(repository)

        // When
        val result = useCase(pokemon)

        // Then
        verify(repository).toggleFavorite(pokemon)
    }

}
