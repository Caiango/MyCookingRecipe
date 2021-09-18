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

    @Query("UPDATE recipes SET name = :name, ingredients = :ingredients, description = :desc, image = :img WHERE id = :id")
    suspend fun updateRecipe(id: Int, name: String, ingredients: String, desc: String, img: String)
}