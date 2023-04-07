package com.ua.inkpad.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {
    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}