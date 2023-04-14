package com.ua.inkpad.di

import android.content.Context
import com.ua.inkpad.utils.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {
    @Provides
    @Singleton
    fun providePreferenceManager(context: Context): PreferenceManager {
        return PreferenceManager(context)
    }
}