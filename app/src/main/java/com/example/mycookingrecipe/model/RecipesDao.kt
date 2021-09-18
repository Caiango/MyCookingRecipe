package com.example.mycookingrecipe.model

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mycookingrecipe.data.Recipe

interface RecipesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: Recipe)

    @Delete
    suspend fun delete(city: Recipe)

    @Query("SELECT * FROM recipes")
    suspend fun getAllFavouriteCities(): List<Recipe>
}