package com.example.mycookingrecipe.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.mycookingrecipe.ui.MainActivity

class LoginViewModel : ViewModel() {

    fun checkLogin(context: Context) {
        Toast.makeText(context, "LOGANDO...", Toast.LENGTH_LONG).show()
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }
}