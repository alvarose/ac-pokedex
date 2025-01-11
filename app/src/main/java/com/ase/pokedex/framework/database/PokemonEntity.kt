package com.ase.pokedex.framework.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val types: String? = null,
    val favorite: Boolean,
)
