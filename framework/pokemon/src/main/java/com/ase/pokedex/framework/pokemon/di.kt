package com.ase.pokedex.framework.pokemon

import androidx.room.Room
import com.ase.pokedex.framework.pokemon.database.PokemonDatabase
import com.ase.pokedex.framework.pokemon.remote.ApiClient
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module

val dataSourceFrameworkModule = module {
    single { Room.databaseBuilder(get(), PokemonDatabase::class.java, "pokemon_db").build() }
    factory { get<PokemonDatabase>().pokemonDao() }
    single { ApiClient.instance }
}

@Module
@ComponentScan
class PokemonFrameworkModule
