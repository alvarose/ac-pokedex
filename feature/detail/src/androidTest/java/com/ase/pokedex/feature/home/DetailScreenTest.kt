package com.ase.pokedex.feature.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.ase.pokedex.Result
import com.ase.pokedex.feature.detail.BACK_BUTTON
import com.ase.pokedex.feature.detail.DetailContent
import com.ase.pokedex.feature.detail.POKEMON_FAVORITE_BUTTON
import com.ase.pokedex.test.samplePokemon
import com.ase.pokedex.ui.common.ERROR_INDICATOR_TAG
import com.ase.pokedex.ui.common.LOADING_INDICATOR_TAG
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val pokemon = samplePokemon(3)

    @Test
    fun whenLoadingState_showProgressIndicator(): Unit = with(composeTestRule) {
        setContent {
            DetailContent(
                state = Result.Loading,
                onBack = {},
                onFavoriteClicked = {}
            )
        }

        onNodeWithTag(LOADING_INDICATOR_TAG).assertIsDisplayed()
    }

    @Test
    fun whenErrorState_showError(): Unit = with(composeTestRule) {
        setContent {
            DetailContent(
                state = Result.Error(RuntimeException("PokeError!")),
                onBack = {},
                onFavoriteClicked = {}
            )
        }

        onNodeWithTag(ERROR_INDICATOR_TAG).assertIsDisplayed()
    }

    @Test
    fun whenSuccessState_pokemonIsDisplayed(): Unit = with(composeTestRule) {
        setContent {
            DetailContent(
                state = Result.Success(pokemon),
                onBack = {},
                onFavoriteClicked = {}
            )
        }

        onNodeWithContentDescription("Image ${pokemon.name}").assertExists()
    }

    @Test
    fun whenOnFavoriteClick_listenerIsCalled(): Unit = with(composeTestRule) {
        var clicked = false
        setContent {
            DetailContent(
                state = Result.Success(pokemon),
                onBack = {},
                onFavoriteClicked = { clicked = true }
            )
        }

        onNodeWithTag(POKEMON_FAVORITE_BUTTON).performClick()

        assertTrue(clicked)
    }

    @Test
    fun whenOnBackClick_listenerIsCalled(): Unit = with(composeTestRule) {
        var clicked = false
        setContent {
            DetailContent(
                state = Result.Success(pokemon),
                onBack = { clicked = true },
                onFavoriteClicked = { }
            )
        }

        onNodeWithTag(BACK_BUTTON).performClick()

        assertTrue(clicked)
    }
}
