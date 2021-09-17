package com.example.mycookingrecipe.ui

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.mycookingrecipe.R
import com.example.mycookingrecipe.data.Recipe
import com.example.mycookingrecipe.databinding.ActivityRecipeBinding
import com.example.mycookingrecipe.utils.Constants
import com.example.mycookingrecipe.viewmodel.RecipeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectedRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeBinding
    private lateinit var selectRecipe: Recipe
    private val recipeViewModel: RecipeViewModel by viewModel()
    private lateinit var DELETE_URL: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mountAppBar()
        assignElements()
    }

    private fun mountAppBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.toolbar.setNavigationOnClickListener { goBackToHome() }
    }

    private fun assignElements() {
        val bundle = intent
        val recipe = bundle.getParcelableExtra<Recipe>(Constants.SELECTED_RECIPE)
        selectRecipe = recipe!!
        showRecipe(recipe)
        DELETE_URL = "recipes/${recipe.id}"
    }

    private fun showRecipe(recipe: Recipe) {
        binding.recipeTitle.text = recipe.name
        binding.txtIngredients.text = recipe.ingredients
        binding.txtPrepare.text = recipe.description
        Glide.with(applicationContext).load(recipe.image).into(binding.recipeIMG)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, UpdateRecipeActivity::class.java)
                intent.putExtra(Constants.SELECTED_RECIPE, selectRecipe)
                startActivity(intent)
                finish()
                return true
            }
            R.id.action_delete -> {
                deleteDialog()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goBackToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun deleteDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(getString(R.string.delete_recipe_dialog))
        dialog.setPositiveButton(getString(R.string.sim)) { _: DialogInterface, _: Int ->
            lifecycleScope.launch {
                recipeViewModel.deleteRecipe(DELETE_URL)
                goBackToHome()
            }
        }
        dialog.setNegativeButton(getString(R.string.cancelar)) { _: DialogInterface, _: Int ->
            Toast.makeText(this, getString(R.string.cancelado), Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }
}