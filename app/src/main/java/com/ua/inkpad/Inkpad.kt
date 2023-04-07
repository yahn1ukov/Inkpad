package com.ua.inkpad

import android.app.Application
import com.google.android.material.color.DynamicColors
import com.ua.inkpad.di.*

class Inkpad : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this@Inkpad))
            .roomModule(RoomModule())
            .adapterModule(AdapterModule())
            .repositoryModule(RepositoryModule())
            .viewModelModule(ViewModelModule())
            .build()
    }
}