package com.example.mycookingrecipe.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mycookingrecipe.data.Recipe
import com.example.mycookingrecipe.databinding.ActivityRecipeBinding
import com.example.mycookingrecipe.utils.Constants

class SelectedRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.toolbar.setNavigationOnClickListener { goBackToHome() }

        val bundle = intent
        val recipe = bundle.getParcelableExtra<Recipe>(Constants.SELECTED_RECIPE)
        showRecipe(recipe!!)

    }

    private fun showRecipe(recipe: Recipe) {
        binding.recipeTitle.text = recipe.name
        binding.txtIngredients.text = recipe.ingredients
        binding.txtPrepare.text = recipe.description
        Glide.with(applicationContext).load(recipe.image).into(binding.recipeIMG)
    }

//    private fun goBackToHome(recipe: Recipe, isChanged: Boolean){
////        val retorno = Intent()
////        retorno.putExtra("oi", recipe)
////        retorno.putExtra("oi", isChanged)
////        setResult(Activity.RESULT_OK, retorno)
//        finish()
//    }

    private fun goBackToHome() {
        finish()
    }
}