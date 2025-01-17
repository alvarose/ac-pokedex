package com.ase.pokedex.feature.detail

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class DetailViewModelModule {

    @Provides
    @ViewModelScoped
    @Named("pokemonId")
    fun providePokemonId(saveStateHandle: SavedStateHandle): Int = saveStateHandle["pokemonId"]!!

}
