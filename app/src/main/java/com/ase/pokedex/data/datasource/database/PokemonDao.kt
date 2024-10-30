package com.ase.pokedex.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    suspend fun fetchPokemonList(): List<PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE id = :id")
    suspend fun findPokemonById(id: Int): PokemonEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemon(pokemon: List<PokemonEntity>)

    @Update
    suspend fun updatePokemon(pokemon: PokemonEntity)

    @Query("SELECT COUNT(*) FROM pokemon")
    suspend fun countPokemon(): Int

    @Query("DELETE FROM pokemon")
    suspend fun clearDatabase();
}
