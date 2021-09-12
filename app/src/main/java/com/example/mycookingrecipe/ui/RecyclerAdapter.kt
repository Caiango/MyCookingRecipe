package com.example.mycookingrecipe.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mycookingrecipe.R
import com.example.mycookingrecipe.data.Recipe
import com.example.mycookingrecipe.databinding.RecipeLayoutBinding

class RecyclerAdapter(
    private val recipesList: List<Recipe>,
    private val context: Context,
    private val onClickCallback: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: RecipeLayoutBinding) : RecyclerView.ViewHolder(view.root) {
        val title = view.recipeTitle
        val img = view.recipeImage
        val layout = view.recipeLay
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_layout, parent, false)
        val binding = RecipeLayoutBinding.bind(view)
        val viewHolder = ViewHolder(binding)
        viewHolder.layout.setOnClickListener {
            val position = viewHolder.adapterPosition
            onClickCallback(position)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipesList[position]
        Glide.with(context).load(recipe.image).into(holder.img)
        holder.title.text = recipe.name
    }

    override fun getItemCount(): Int = recipesList.size
}