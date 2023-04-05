package com.ua.inkpad

import android.app.Application
import com.google.android.material.color.DynamicColors

class Inkpad : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}