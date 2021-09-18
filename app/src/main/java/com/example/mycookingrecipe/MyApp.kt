package com.example.mycookingrecipe

import android.app.Application
import com.example.mycookingrecipe.di.daoModule
import com.example.mycookingrecipe.di.repositoryModule
import com.example.mycookingrecipe.di.viewModelModule
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

            modules(viewModelModule)
            modules(daoModule)
            modules(repositoryModule)
        }
    }
}