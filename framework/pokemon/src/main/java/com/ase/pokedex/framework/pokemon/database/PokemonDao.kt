package com.ase.pokedex.framework.pokemon.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM PokemonEntity")
    fun fetchPokemonList(): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM PokemonEntity WHERE id = :id")
    fun findPokemonById(id: Int): Flow<PokemonEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemon(pokemon: List<PokemonEntity>)

    @Update
    suspend fun updatePokemon(pokemon: PokemonEntity)

    @Query("SELECT COUNT(*) FROM PokemonEntity")
    suspend fun countPokemon(): Int

    @Query("DELETE FROM PokemonEntity")
    suspend fun clearDatabase();
}
