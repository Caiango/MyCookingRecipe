package com.example.mycookingrecipe.di

import com.example.mycookingrecipe.viewmodel.RecipeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        RecipeViewModel()
    }
}