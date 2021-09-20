package com.example.mycookingrecipe.repository

import com.example.mycookingrecipe.data.Recipe
import com.example.mycookingrecipe.model.RecipesDao
import com.example.mycookingrecipe.model.RepositoryContract

class RecipesRepository(private val dao: RecipesDao) : RepositoryContract {

    override suspend fun insert(recipe: Recipe) {
        dao.insert(recipe)
    }

    override suspend fun delete(recipe: Recipe) {
        dao.delete(recipe)
    }

    override suspend fun getAllRecipes(): List<Recipe> {
        return dao.getAllRecipes()
    }

    override suspend fun getRecipe(id: Int): Recipe {
        return dao.getRecipe(id)
    }

    override suspend fun updateRecipe(recipe: Recipe) {
        dao.updateRecipe(
            recipe.id,
            recipe.name,
            recipe.ingredients,
            recipe.description,
            recipe.image
        )
    }

}