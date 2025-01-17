package com.ase.pokedex.framework.pokemon

import androidx.room.Room
import com.ase.pokedex.domain.pokemon.data.datasource.LocalDataSource
import com.ase.pokedex.domain.pokemon.data.datasource.RemoteDataSource
import com.ase.pokedex.framework.pokemon.database.PokemonDatabase
import com.ase.pokedex.framework.pokemon.remote.ApiClient
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataSourceFrameworkModule = module {
    single { Room.databaseBuilder(get(), PokemonDatabase::class.java, "pokemon_db").build() }
    factory { get<PokemonDatabase>().pokemonDao() }
    single { ApiClient.instance }
}

val pokemonFrameworkModule = module {
    factoryOf(::PokemonLocalDataSource) bind LocalDataSource::class
    factoryOf(::PokemonRemoteDataSource) bind RemoteDataSource::class
}
