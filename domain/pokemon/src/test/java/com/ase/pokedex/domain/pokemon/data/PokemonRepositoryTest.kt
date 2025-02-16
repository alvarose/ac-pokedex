package com.ase.pokedex.domain.pokemon.data

import com.ase.pokedex.domain.pokemon.data.datasource.LocalDataSource
import com.ase.pokedex.domain.pokemon.data.datasource.RemoteDataSource
import com.ase.pokedex.domain.pokemon.models.Pokemon
import com.ase.pokedex.domain.pokemon.usecases.samplePokemon
import com.ase.pokedex.domain.pokemon.usecases.samplePokemonList
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argThat
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class PokemonRepositoryTest {

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    private lateinit var repository: PokemonRepository

    @Before
    fun setup() {
        repository = PokemonRepository(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }

    @Test
    fun `Pokemon list load from local data source`() = runBlocking {
        // Given
        val localPokemonList = samplePokemonList(1, 2, 3)
        whenever(localDataSource.pokemonList).thenReturn(flowOf(localPokemonList))

        // When
        val result = repository.pokemonList

        // Then
        assertEquals(localPokemonList, result.first())
    }

    @Test
    fun `Pokemon list load from remote data source when local is empty`() = runBlocking {
        // Given
        val localPokemonList = emptyList<Pokemon>()
        val remotePokemonList = samplePokemonList(1, 2, 3)
        whenever(localDataSource.pokemonList).thenReturn(flowOf(localPokemonList))
        whenever(remoteDataSource.fetchPokemonList()).thenReturn(remotePokemonList)

        // When
        repository.pokemonList.first()

        // Then
        verify(localDataSource).savePokemonList(remotePokemonList)
    }

    @Test
    fun `Updating a Pokemon's favorite status`() = runBlocking {
        // Given
        val pokemon = samplePokemon(5)

        // When
        repository.toggleFavorite(pokemon)

        // Then
        verify(localDataSource).updatePokemon(argThat { id == 5 })
    }

    @Test
    fun `Switching favorite an unfavorite Pokemon`() = runBlocking {
        // Given
        val pokemon = samplePokemon(5).copy(favorite = false)

        // When
        repository.toggleFavorite(pokemon)

        // Then
        verify(localDataSource).updatePokemon(argThat { id == 5 && favorite })
    }

    @Test
    fun `Switching unfavorite a favorite Pokemon`() = runBlocking {
        // Given
        val pokemon = samplePokemon(5).copy(favorite = true)

        // When
        repository.toggleFavorite(pokemon)

        // Then
        verify(localDataSource).updatePokemon(argThat { id == 5 && !favorite })
    }
}
