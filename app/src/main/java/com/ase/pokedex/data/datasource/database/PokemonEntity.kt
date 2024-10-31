package com.ase.pokedex.data.datasource.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val types: String? = null,
    val favorite: Boolean,
)
