package com.example.mycookingrecipe.service

import com.example.mycookingrecipe.data.Resp
import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET("recipes")
    fun getRecipes(): Call<Resp>
}