package com.example.mycookingrecipe.service

import android.content.Context
import android.util.Log
import com.example.mycookingrecipe.data.Resp
import retrofit2.Callback
import retrofit2.Response

class Call {
    companion object {
        fun call(context: Context, callback: (Resp?, Context) -> Unit) {
            val call = RetrofitInitializer().repoService().getRecipes()

            call.enqueue(object : Callback<Resp> {
                override fun onResponse(call: retrofit2.Call<Resp>, resp: Response<Resp>) {
                    resp?.body()?.let {
                        val response: Resp = it
                        callback(response, context)
                    }
                }

                override fun onFailure(call: retrofit2.Call<Resp>, t: Throwable) {
                    Log.d("erro", t.toString())
                    callback(null, context)
                }

            })
        }
    }
}