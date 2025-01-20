package com.ase.pokedex

import android.app.Application
import com.ase.pokedex.framework.pokemon.database.PokemonDatabase
import androidx.room.Room

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
