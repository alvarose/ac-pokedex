package com.ase.pokedex.framework.pokemon

import android.app.Application
import androidx.room.Room
import com.ase.pokedex.domain.pokemon.data.datasource.LocalDataSource
import com.ase.pokedex.domain.pokemon.data.datasource.RemoteDataSource
import com.ase.pokedex.framework.pokemon.database.PokemonDatabase
import com.ase.pokedex.framework.pokemon.remote.ApiClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PokemonDataSourceModule {
    @Binds
    abstract fun bindLocalDataSource(localDataSource: PokemonLocalDataSource): LocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: PokemonRemoteDataSource): RemoteDataSource
}

@Module
@InstallIn(SingletonComponent::class)
internal object PokemonFrameworkModule {

    @Provides
    @Singleton
    fun providePokemonDao(database: PokemonDatabase) = database.pokemonDao()

    @Provides
    @Singleton
    fun provideApiClient(@Named("apiUrl") apiUrl: String) = ApiClient(apiUrl).instance

}

@Module
@InstallIn(SingletonComponent::class)
object PokemonDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(app, PokemonDatabase::class.java, "pokemon_db").build()

    @Provides
    @Singleton
    @Named("apiUrl")
    fun provideApiUrl(): String = BuildConfig.API_URL

}
