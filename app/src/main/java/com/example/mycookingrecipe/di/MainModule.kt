package com.example.mycookingrecipe.di

import com.example.mycookingrecipe.model.DatabaseInstance
import com.example.mycookingrecipe.model.RepositoryContract
import com.example.mycookingrecipe.repository.RecipesRepository
import com.example.mycookingrecipe.viewmodel.LoginViewModel
import com.example.mycookingrecipe.viewmodel.RecipeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModelModule = module {
    viewModel {
        RecipeViewModel(get())
    }
}

val loginViewModelModule = module {
    viewModel {
        LoginViewModel()
    }
}

val daoModule = module {
    single {
        DatabaseInstance.getInstance(androidContext())?.recipesDao
    }
}

val repositoryModule = module {
    single<RepositoryContract> {
        RecipesRepository(get())
    }
}