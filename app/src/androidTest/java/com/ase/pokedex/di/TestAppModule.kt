package com.ase.pokedex.di

import android.app.Application
import androidx.room.Room
import com.ase.pokedex.framework.pokemon.PokemonDatabaseModule
import com.ase.pokedex.framework.pokemon.database.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Named
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [PokemonDatabaseModule::class]
)
object TestAppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): PokemonDatabase {
        val db = Room.inMemoryDatabaseBuilder(
            app,
            PokemonDatabase::class.java
        )
            .setTransactionExecutor(Dispatchers.Main.asExecutor())
            .allowMainThreadQueries()
            .build()
        return db
    }

    @Provides
    @Singleton
    @Named("apiUrl")
    fun provideApiUrl(): String = "http://localhost:8080"
}
