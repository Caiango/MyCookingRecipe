package com.example.mycookingrecipe.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycookingrecipe.data.Recipe
import com.example.mycookingrecipe.databinding.FragmentMainBinding
import com.example.mycookingrecipe.viewmodel.RecipeViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var recipeList = listOf<Recipe>()
    private var filteredRecipeList = listOf<Recipe>()
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: RecyclerAdapter
    lateinit var recipeViewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)
        recipeViewModel.getRecipes()

        recipeViewModel.recipeList.observe(viewLifecycleOwner, {
            adapter = RecyclerAdapter(it, requireContext())
            recycler.adapter = adapter
            recipeList = it
        })

        recipeViewModel.recipeListFiltered.observe(viewLifecycleOwner, {
            adapter = RecyclerAdapter(it, requireContext())
            recycler.adapter = adapter
            filteredRecipeList = it
        })
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        recycler = binding.recyclerRecipe
        binding.editTextTextPersonName.doOnTextChanged { text, start, before, count ->
            val filter = binding.editTextTextPersonName.text.toString()
            recipeViewModel.filterList(recipeList, filter)
        }
        adapter = RecyclerAdapter(recipeList, requireContext())
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.setHasFixedSize(true)
        recycler.adapter
    }

    //para brincar
    object TextChangedListener : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}