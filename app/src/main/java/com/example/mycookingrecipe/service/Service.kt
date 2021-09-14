package com.example.mycookingrecipe.service

import com.example.mycookingrecipe.data.Recipe
import com.example.mycookingrecipe.data.Resp
import com.example.mycookingrecipe.data.ReturnFromApi
import retrofit2.Call
import retrofit2.http.*

interface Service {
    @GET("recipes")
    fun getRecipes(): Call<Resp>

    @POST("recipes")
    fun insertRecipe(@Body recipe: Recipe): Call<ReturnFromApi>

    @PUT
    fun updateRecipe(@Body recipe: Recipe, @Url url: String): Call<ReturnFromApi>

    @DELETE
    fun deleteRecipe(@Url url: String): Call<ReturnFromApi>
}