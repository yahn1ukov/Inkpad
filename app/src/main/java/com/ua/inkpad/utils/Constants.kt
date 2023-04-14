package com.ua.inkpad.utils

import androidx.appcompat.app.AppCompatDelegate

class Constants {
    companion object {
        const val DB_NAME = "Inkpad.db"

        const val DATA_STORE_NAME = "Inkpad.store"
        const val PREFERENCE_THEME_MODE = "theme_mode"

        const val MODE_NIGHT_FOLLOW_SYSTEM = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        const val MODE_NIGHT_NO = AppCompatDelegate.MODE_NIGHT_NO
        const val MODE_NIGHT_YES = AppCompatDelegate.MODE_NIGHT_YES

        val THEME_MODES = arrayOf("System", "Light", "Dark")
        val PRIORITIES = arrayOf("High", "Medium", "Low")

        const val HIGH_PRIORITY = "High"
        const val MEDIUM_PRIORITY = "Medium"
        const val LOW_PRIORITY = "Low"

        const val SYSTEM_THEME_MODE = "System"
        const val LIGHT_THEME_MODE = "Light"
        const val DARK_THEME_MODE = "Dark"
    }
}