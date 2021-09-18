package com.example.mycookingrecipe.model

import androidx.room.*
import com.example.mycookingrecipe.data.Recipe

@Dao
interface RecipesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe)

    @Delete
    suspend fun delete(recipe: Recipe)

    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE id = :id")
    suspend fun getRecipe(id: Int): Recipe
}