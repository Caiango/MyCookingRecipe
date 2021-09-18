package com.example.mycookingrecipe.repository

import com.example.mycookingrecipe.data.Recipe
import com.example.mycookingrecipe.model.RecipesDao

class RecipesRepository(private val dao: RecipesDao) {

    suspend fun insert(recipe: Recipe) {
        dao.insert(recipe)
    }

    suspend fun delete(recipe: Recipe) {
        dao.delete(recipe)
    }

    suspend fun getAllRecipes() {
        dao.getAllRecipes()
    }

    suspend fun getRecipe(id: Int) {
        dao.getRecipe(id)
    }
}