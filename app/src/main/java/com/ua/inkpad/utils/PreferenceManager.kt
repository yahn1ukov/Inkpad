package com.ua.inkpad.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.ua.inkpad.utils.Constants.Companion.DARK_THEME_MODE
import com.ua.inkpad.utils.Constants.Companion.DATA_STORE_NAME
import com.ua.inkpad.utils.Constants.Companion.LIGHT_THEME_MODE
import com.ua.inkpad.utils.Constants.Companion.MODE_NIGHT_FOLLOW_SYSTEM
import com.ua.inkpad.utils.Constants.Companion.MODE_NIGHT_NO
import com.ua.inkpad.utils.Constants.Companion.MODE_NIGHT_YES
import com.ua.inkpad.utils.Constants.Companion.PREFERENCE_THEME_MODE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class PreferenceManager @Inject constructor(
    context: Context
) {
    private val dataStore = context.createDataStore(name = DATA_STORE_NAME)

    companion object {
        private val THEME_MODE = preferencesKey<Int>(PREFERENCE_THEME_MODE)
    }

    suspend fun setThemeMode(themeMode: String) {
        dataStore.edit { preferences ->
            preferences[THEME_MODE] = when (themeMode) {
                LIGHT_THEME_MODE -> MODE_NIGHT_NO
                DARK_THEME_MODE -> MODE_NIGHT_YES
                else -> MODE_NIGHT_FOLLOW_SYSTEM
            }
        }
    }

    val getThemeMode: Flow<Int> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences -> preferences[THEME_MODE] ?: MODE_NIGHT_FOLLOW_SYSTEM }
}