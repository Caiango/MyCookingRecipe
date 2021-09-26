package com.example.mycookingrecipe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycookingrecipe.data.Recipe
import com.example.mycookingrecipe.data.Resp
import com.example.mycookingrecipe.model.RepositoryContract
import com.example.mycookingrecipe.repository.RecipesRepository
import com.example.mycookingrecipe.service.Call
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeViewModel(private val repositoryContract: RepositoryContract) : ViewModel() {

    val recipeList: MutableLiveData<List<Recipe>> = MutableLiveData()
    val recipeListFiltered: MutableLiveData<List<Recipe>> = MutableLiveData()

    fun getRecipes() {
        viewModelScope.launch {
            Call.callGetAll(this@RecipeViewModel::callbackFromGetRecipes)
        }
    }

    fun getMyRecipes() {
        viewModelScope.launch {

        }
    }

    private fun callbackFromGetRecipes(response: Resp?) {
        if (response != null) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    response.recipes.forEach {
                        repositoryContract.insert(it)
                    }
                    recipeList.postValue(repositoryContract.getAllRecipes())
                }
            }
        } else {
            viewModelScope.launch { recipeList.postValue(repositoryContract.getAllRecipes()) }

        }
    }

    fun insertNewRecipe(recipe: Recipe, insertCallback: (String) -> Unit) {
        viewModelScope.launch {
            Call.callInsert(recipe)
            withContext(Dispatchers.IO) {
                repositoryContract.insert(recipe)
            }
        }
    }

    fun updateRecipe(
        recipe: Recipe,
        url: String,
        updateCallback: (String, Recipe) -> Unit
    ) {
        viewModelScope.launch {
            Call.callUpdate(recipe, url, updateCallback)
        }
    }

    fun deleteRecipe(url: String, deleteCallback: (String) -> Unit) {
        viewModelScope.launch {
            Call.callDelete(url, deleteCallback)
        }
    }

    fun locallyDelete(recipe: Recipe) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repositoryContract.delete(recipe)
            }
        }
    }

    fun locallyInsert(recipe: Recipe) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repositoryContract.insert(recipe)
            }
        }
    }

    fun locallyUpdate(recipe: Recipe) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repositoryContract.updateRecipe(recipe)
            }
        }
    }

    fun filterList(fulList: List<Recipe>, filter: String) {
        if (filter != "") {
            val filteredList = fulList.filter {
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