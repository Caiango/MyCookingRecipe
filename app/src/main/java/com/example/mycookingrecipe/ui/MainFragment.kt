package com.example.mycookingrecipe.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycookingrecipe.data.Recipe
import com.example.mycookingrecipe.databinding.FragmentMainBinding
import com.example.mycookingrecipe.utils.Constants
import com.example.mycookingrecipe.viewmodel.RecipeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!
    private var recipeList = listOf<Recipe>()
    private var filteredRecipeList = listOf<Recipe>()
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: RecyclerAdapter

    private val recipeViewModel: RecipeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        recipeViewModel.getRecipes()

        recipeViewModel.recipeList.observe(viewLifecycleOwner, {
            adapter = RecyclerAdapter(it, requireContext(), this::setIntentSelectedRecipe)
            recycler.adapter = adapter
            recipeList = it
        })

        recipeViewModel.recipeListFiltered.observe(viewLifecycleOwner, {
            adapter = RecyclerAdapter(it, requireContext(), this::setIntentSelectedRecipe)
            recycler.adapter = adapter
            filteredRecipeList = it
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = binding.recyclerRecipe
        binding.editTextTextPersonName.doOnTextChanged { _, _, _, _ ->
            val filter = binding.editTextTextPersonName.text.toString()
            recipeViewModel.filterList(recipeList, filter)
        }
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.setHasFixedSize(true)
    }

    private fun setIntentSelectedRecipe(pos: Int) {
        val intent = Intent(requireContext(), SelectedRecipeActivity::class.java)
        if (!isFiltered()) {
            intent.putExtra(Constants.SELECTED_RECIPE, recipeList[pos])
        } else {
            intent.putExtra(Constants.SELECTED_RECIPE, filteredRecipeList[pos])
        }
        startActivity(intent)
        childFragmentManager.beginTransaction().remove(MainFragment()).commitAllowingStateLoss()
    }

    private fun isFiltered(): Boolean {
        return adapter.itemCount != recipeList.size
    }

    override fun onResume() {
        super.onResume()
        recipeViewModel.getRecipes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}