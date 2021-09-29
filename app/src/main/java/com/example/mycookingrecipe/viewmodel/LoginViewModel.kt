package com.example.mycookingrecipe.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val successfullLogin: MutableLiveData<Boolean> = MutableLiveData()
    val existingLogin: MutableLiveData<Boolean> = MutableLiveData()
    val createdLogin: MutableLiveData<Boolean> = MutableLiveData()

    fun checkLogin(context: Context) {
        successfullLogin.postValue(true)
    }

    fun createLogin(){
        createdLogin.postValue(true)
    }
}