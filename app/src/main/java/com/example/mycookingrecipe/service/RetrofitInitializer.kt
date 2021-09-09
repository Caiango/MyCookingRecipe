package com.example.mycookingrecipe.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofit =
        Retrofit.Builder()
            .baseUrl("http://")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun repoService(): Service = retrofit.create(Service::class.java)
}