package com.example.mycookingrecipe.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mycookingrecipe.data.Resp
import com.example.mycookingrecipe.databinding.FragmentMainBinding
import com.example.mycookingrecipe.service.Call

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        Call.call(this::callBackFromSearch)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        binding.editTextTextPersonName.addTextChangedListener(textChangedListener)
    }

    fun callBackFromSearch(response: Resp?) {

        Log.i("LISTA", response?.recipes.toString())

    }

    //para brincar
    object textChangedListener : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            Log.i("object", "object")
        }

        override fun afterTextChanged(s: Editable?) {

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}