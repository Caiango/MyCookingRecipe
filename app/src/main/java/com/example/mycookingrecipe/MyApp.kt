package com.example.mycookingrecipe

import android.app.Application
import com.example.mycookingrecipe.di.daoModule
import com.example.mycookingrecipe.di.loginViewModelModule
import com.example.mycookingrecipe.di.mainViewModelModule
import com.example.mycookingrecipe.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApp)

            modules(mainViewModelModule)
            modules(daoModule)
            modules(repositoryModule)
            modules(loginViewModelModule)
        }
    }
}