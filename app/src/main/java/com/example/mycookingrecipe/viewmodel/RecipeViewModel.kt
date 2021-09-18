package com.example.mycookingrecipe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycookingrecipe.data.Recipe
import com.example.mycookingrecipe.data.Resp
import com.example.mycookingrecipe.repository.RecipesRepository
import com.example.mycookingrecipe.service.Call
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipesRepository) : ViewModel() {

    val recipeList: MutableLiveData<List<Recipe>> = MutableLiveData()
    val recipeListFiltered: MutableLiveData<List<Recipe>> = MutableLiveData()

    fun getRecipes() {
        viewModelScope.launch {
            Call.callGetAll(this@RecipeViewModel::callbackFromGetRecipes)
        }
    }

    fun insertRecipe(recipe: Recipe) {
        viewModelScope.launch {
            Call.callInsert(recipe)
        }
    }

    fun updateRecipe(
        recipe: Recipe,
        url: String,
        callback: (Recipe) -> Unit
    ) {
        viewModelScope.launch {
            Call.callUpdate(recipe, url)
            callback(recipe)
        }
    }

    fun deleteRecipe(url: String) {
        Call.callDelete(url)
    }

    private fun callbackFromGetRecipes(response: Resp?) {
        if (response != null) {
            recipeList.postValue(response.recipes)
        }
    }

    fun filterList(fulList: List<Recipe>, filter: String) {
        if (filter != "") {
            var filteredList = fulList.filter {
                it.name.contains(
                    filter,
                    ignoreCase = true
                ) || it.ingredients.contains(filter, ignoreCase = true)
            }
            recipeListFiltered.postValue(filteredList)
        } else {
            recipeList.postValue(fulList)
        }
    }
}