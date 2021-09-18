package com.example.mycookingrecipe.service

import android.util.Log
import com.example.mycookingrecipe.data.Recipe
import com.example.mycookingrecipe.data.Resp
import com.example.mycookingrecipe.data.ReturnFromApi
import retrofit2.Callback
import retrofit2.Response

class Call {
    companion object {
        fun callGetAll(callback: (Resp?) -> Unit) {
            val call = RetrofitInitializer().repoService().getRecipes()

            call.enqueue(object : Callback<Resp> {
                override fun onResponse(call: retrofit2.Call<Resp>, resp: Response<Resp>) {
                    resp?.body()?.let {
                        Log.d("sucesso", "lista recuperada")
                        val response: Resp = it
                        callback(response)
                    }
                }

                override fun onFailure(call: retrofit2.Call<Resp>, t: Throwable) {
                    Log.d("erro", t.toString())
                    callback(null)
                }
            })
        }

        fun callInsert(recipe: Recipe) {
            val call = RetrofitInitializer().repoService().insertRecipe(recipe)

            call.enqueue(object : Callback<ReturnFromApi> {
                override fun onResponse(
                    call: retrofit2.Call<ReturnFromApi>,
                    resp: Response<ReturnFromApi>
                ) {
                    resp?.body()?.let {
                        val response: ReturnFromApi = it
                        Log.d("sucesso", response.message)
                    }
                }

                override fun onFailure(call: retrofit2.Call<ReturnFromApi>, t: Throwable) {
                    Log.d("erro", t.toString())
                }
            })
        }

        fun callUpdate(
            recipe: Recipe,
            url: String,
            updateCallback: (String, Recipe) -> Unit
        ) {
            val call = RetrofitInitializer().repoService().updateRecipe(recipe, url)

            call.enqueue(object : Callback<ReturnFromApi> {
                override fun onResponse(
                    call: retrofit2.Call<ReturnFromApi>,
                    resp: Response<ReturnFromApi>
                ) {
                    resp?.body()?.let {
                        val response: ReturnFromApi = it
                        updateCallback(response.message, recipe)
                    }
                }

                override fun onFailure(call: retrofit2.Call<ReturnFromApi>, t: Throwable) {
                    Log.d("updateError", t.toString())
                    updateCallback("404", recipe)
                }
            })
        }

        fun callDelete(url: String) {
            val call = RetrofitInitializer().repoService().deleteRecipe(url)

            call.enqueue(object : Callback<ReturnFromApi> {
                override fun onResponse(
                    call: retrofit2.Call<ReturnFromApi>,
                    resp: Response<ReturnFromApi>
                ) {
                    resp?.body()?.let {
                        val response: ReturnFromApi = it
                        Log.d("sucesso", response.message)
                    }
                }

                override fun onFailure(call: retrofit2.Call<ReturnFromApi>, t: Throwable) {
                    Log.d("erro", t.toString())
                }
            })
        }
    }
}