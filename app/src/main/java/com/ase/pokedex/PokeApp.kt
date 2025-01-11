package com.ase.pokedex

import android.app.Application
import androidx.room.Room
import com.ase.pokedex.data.datasource.database.PokemonDatabase

class PokeApp : Application() {
    lateinit var db: PokemonDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, PokemonDatabase::class.java, "pokemon_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}
