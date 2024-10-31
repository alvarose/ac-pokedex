package com.ase.pokedex.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PokemonEntity::class], version = 2, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
