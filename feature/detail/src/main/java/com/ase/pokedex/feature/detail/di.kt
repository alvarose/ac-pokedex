package com.ase.pokedex.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.ase.pokedex.ui.navigation.PokemonDetail
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
class DetailViewModelModule {

    @Provides
    @ViewModelScoped
    @PokemonId
    fun providePokemonId(savedStateHandle: SavedStateHandle): Int {
        return savedStateHandle.toRoute<PokemonDetail>().pokemonId
    }

}

@Qualifier
annotation class PokemonId
