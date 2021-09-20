package com.example.mycookingrecipe.model

import com.example.mycookingrecipe.data.Recipe

interface RepositoryContract {
    suspend fun insert(recipe: Recipe)

    suspend fun delete(recipe: Recipe)

    suspend fun getAllRecipes(): List<Recipe>

    suspend fun getRecipe(id: Int): Recipe

    suspend fun updateRecipe(recipe: Recipe)

}