package com.example.mycookingrecipe.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mycookingrecipe.R
import com.example.mycookingrecipe.data.Recipe
import com.example.mycookingrecipe.databinding.ActivityRecipeBinding
import com.example.mycookingrecipe.databinding.RecipeLayoutBinding
import com.example.mycookingrecipe.utils.Constants

class RecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bundle = intent
        val recipe = bundle.getParcelableExtra<Recipe>(Constants.SELECTED_RECIPE)
        Toast.makeText(this, recipe!!.name, Toast.LENGTH_SHORT).show()

        binding.bttt.setOnClickListener { v ->
            val retorno = Intent()
            retorno.putExtra("oi", recipe)
            setResult(Activity.RESULT_OK, retorno)
            finish()
        }
    }
}