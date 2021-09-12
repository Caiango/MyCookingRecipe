package com.example.mycookingrecipe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycookingrecipe.data.Recipe
import com.example.mycookingrecipe.data.Resp
import com.example.mycookingrecipe.service.Call
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    val recipeList: MutableLiveData<List<Recipe>> = MutableLiveData()
    val recipeListFiltered: MutableLiveData<List<Recipe>> = MutableLiveData()

    fun getRecipes() {
        viewModelScope.launch {
            Call.call(this@RecipeViewModel::callbackFromGetRecipes)
        }
    }

    fun callbackFromGetRecipes(response: Resp?) {
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