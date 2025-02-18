package com.ase.pokedex.feature.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.ase.pokedex.Result
import com.ase.pokedex.test.samplePokemonList
import com.ase.pokedex.ui.common.ERROR_INDICATOR_TAG
import com.ase.pokedex.ui.common.LOADING_INDICATOR_TAG
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenLoadingState_showProgressIndicator(): Unit = with(composeTestRule) {
        setContent {
            HomeContent(
                state = Result.Loading,
                onPokemonClick = {}
            )
        }

        onNodeWithTag(LOADING_INDICATOR_TAG).assertIsDisplayed()
    }

    @Test
    fun whenErrorState_showError(): Unit = with(composeTestRule) {
        setContent {
            HomeContent(
                state = Result.Error(RuntimeException("PokeError!")),
                onPokemonClick = {}
            )
        }

        onNodeWithTag(ERROR_INDICATOR_TAG).assertIsDisplayed()
    }

    @Test
    fun whenSuccessState_showPokedex(): Unit = with(composeTestRule) {
        setContent {
            HomeContent(
                state = Result.Success(samplePokemonList(1, 2, 3)),
                onPokemonClick = {}
            )
        }

        onNodeWithText("Pokemon 1").assertExists()
    }


    @Test
    fun whenOnPokemonClick_listenerIsCalled(): Unit = with(composeTestRule) {
        var clickedPokemonId = -1
        val pokemonList = samplePokemonList(1, 2, 3)
        setContent {
            HomeContent(
                state = Result.Success(pokemonList),
                onPokemonClick = { clickedPokemonId = it.id }
            )
        }

        onNodeWithText("Pokemon 2").performClick()

        assertEquals(2, clickedPokemonId)
    }
}
