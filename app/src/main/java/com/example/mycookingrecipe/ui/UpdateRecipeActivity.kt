package com.example.mycookingrecipe.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mycookingrecipe.R
import com.example.mycookingrecipe.data.Recipe
import com.example.mycookingrecipe.databinding.ActivityUpdateRecipeBinding
import com.example.mycookingrecipe.utils.Constants
import com.example.mycookingrecipe.viewmodel.RecipeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateRecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateRecipeBinding
    private lateinit var recipe: Recipe
    private lateinit var title: String
    private lateinit var ingredients: String
    private lateinit var prepare: String
    private lateinit var etPrepare: EditText
    private lateinit var etTitle: EditText
    private lateinit var etIngredients: EditText
    private lateinit var etImg: ImageView
    private val recipeViewModel: RecipeViewModel by viewModel()
    private lateinit var UPDATE_URL: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mountAppBar()
        assignElements()
        mountEditableRecipe(title, ingredients, prepare)
    }

    private fun mountAppBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.toolbar.setNavigationOnClickListener { goBackToHome() }
    }

    private fun assignElements() {
        val bundle = intent
        recipe = bundle.getParcelableExtra(Constants.SELECTED_RECIPE)!!
        UPDATE_URL = "recipes/${recipe.id}"

        title = recipe.name
        ingredients = recipe.ingredients
        prepare = recipe.description

        etIngredients = binding.txtIngredients
        etPrepare = binding.txtPrepare
        etTitle = binding.recipeTitle
        etImg = binding.recipeIMG
        Glide.with(applicationContext).load(recipe.image).into(etImg)
    }

    private fun mountEditableRecipe(title: String, ingredients: String, prepare: String) {
        binding.recipeTitle.setText(title)
        binding.txtIngredients.setText(ingredients)
        binding.txtPrepare.setText(prepare)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_update -> {
                updateRecipe()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateRecipe() {
        val updatedTitle = etTitle.text.toString()
        val updatedImg = recipe.image
        val updatedPrepare = etPrepare.text.toString()
        val updatedIngredients = etIngredients.text.toString()
        val updatedRecipe =
            Recipe(recipe.id, updatedTitle, updatedIngredients, updatedPrepare, updatedImg)

        recipeViewModel.updateRecipe(
            updatedRecipe,
            UPDATE_URL,
            this::updateCallback
        )
    }

    private fun goBackToHome() {
        backToSelectedRecipeScreen()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        backToSelectedRecipeScreen()
    }

    private fun backToSelectedRecipeScreen() {
        val intent = Intent(this, SelectedRecipeActivity::class.java)
        intent.putExtra(Constants.SELECTED_RECIPE, recipe)
        startActivity(intent)
        finish()
    }

    private fun updateCallback(message: String, recipe: Recipe) {
        if (message == "404") {
            Toast.makeText(applicationContext, getString(R.string.onError), Toast.LENGTH_LONG)
                .show()
            callMainScreen()
        } else {
            Toast.makeText(
                applicationContext,
                getString(R.string.onSuccess_Update),
                Toast.LENGTH_LONG
            )
                .show()
            recipeViewModel.locallyUpdate(recipe)
            callSelectRecipeScreenSuccess(recipe)
        }
    }

    private fun callSelectRecipeScreenSuccess(updatedRecipe: Recipe) {
        val intent = Intent(this, SelectedRecipeActivity::class.java)
        intent.putExtra(Constants.SELECTED_RECIPE, updatedRecipe)
        startActivity(intent)
        finish()
    }

    private fun callMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}