package com.example.mycookingrecipe.service

import com.example.mycookingrecipe.data.Recipe
import com.example.mycookingrecipe.data.Resp
import com.example.mycookingrecipe.data.ReturnFromApi
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Service {
    @GET("recipes")
    fun getRecipes(): Call<Resp>

    @POST("recipes")
    fun insertRecipe(@Body recipe: Recipe): Call<ReturnFromApi>

    @POST("recipes/")
    fun updateRecipe(@Body recipe: Recipe)
}