package com.ua.inkpad.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.ua.inkpad.Inkpad
import com.ua.inkpad.R
import com.ua.inkpad.presentation.settings.viewmodels.SettingsViewModel
import com.ua.inkpad.utils.Constants.Companion.MODE_NIGHT_FOLLOW_SYSTEM
import com.ua.inkpad.utils.Constants.Companion.MODE_NIGHT_NO
import com.ua.inkpad.utils.Constants.Companion.MODE_NIGHT_YES

import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Inkpad.appComponent.inject(this@MainActivity)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
        supportActionBar?.apply {
            elevation = 0.0f
        }

        checkThemeMode()
    }

    private fun checkThemeMode() {
        settingsViewModel.getTheme.observe(this) {
            when (it) {
                MODE_NIGHT_FOLLOW_SYSTEM -> AppCompatDelegate.setDefaultNightMode(it)
                MODE_NIGHT_NO -> AppCompatDelegate.setDefaultNightMode(it)
                MODE_NIGHT_YES -> AppCompatDelegate.setDefaultNightMode(it)
            }
        }
    }
}