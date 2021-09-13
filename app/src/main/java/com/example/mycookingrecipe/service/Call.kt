package com.example.mycookingrecipe.service

import android.util.Log
import com.example.mycookingrecipe.data.Recipe
import com.example.mycookingrecipe.data.Resp
import retrofit2.Callback
import retrofit2.Response

class Call {
    companion object {
        fun callGetAll(callback: (Resp?) -> Unit) {
            val call = RetrofitInitializer().repoService().getRecipes()

            call.enqueue(object : Callback<Resp> {
                override fun onResponse(call: retrofit2.Call<Resp>, resp: Response<Resp>) {
                    resp?.body()?.let {
                        Log.d("sucesso", "sucesso")
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

            call.enqueue(object : Callback<String> {
                override fun onResponse(call: retrofit2.Call<String>, resp: Response<String>) {
                    resp?.body()?.let {

                        val response: String = it
                        Log.d("sucesso", response)
                        //callback(response)
                    }
                }

                override fun onFailure(call: retrofit2.Call<String>, t: Throwable) {
                    Log.d("erro", t.toString())
                    //callback(null)
                }

            })
        }
    }
}