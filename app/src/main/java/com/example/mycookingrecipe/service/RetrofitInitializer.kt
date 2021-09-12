package com.example.mycookingrecipe.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofit =
        Retrofit.Builder()
            .baseUrl("http://192.168.1.2:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun repoService(): Service = retrofit.create(Service::class.java)
}