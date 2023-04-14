package com.ua.inkpad.presentation.settings.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ua.inkpad.utils.PreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(private val preferenceManager: PreferenceManager) :
    ViewModel() {
    val getTheme = preferenceManager.getThemeMode.asLiveData(Dispatchers.IO)

    fun setThemeMode(themeMode: String) {
        viewModelScope.launch {
            preferenceManager.setThemeMode(themeMode)
        }
    }
}