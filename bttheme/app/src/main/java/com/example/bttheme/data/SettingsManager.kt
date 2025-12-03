package com.example.bttheme.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Khởi tạo DataStore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_settings")

// Tên các theme
object AppTheme {
    const val LIGHT = "LIGHT"
    const val DARK = "DARK"
    const val MAGENTA = "MAGENTA"
    const val BLUE = "BLUE"
}

class SettingsManager(private val context: Context) {

    // Key để lưu theme
    private val THEME_KEY = stringPreferencesKey("app_theme")

    // Flow để đọc theme. Mặc định là LIGHT
    val themeFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[THEME_KEY] ?: AppTheme.LIGHT
        }

    // Hàm để lưu theme
    suspend fun saveTheme(themeName: String) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = themeName
        }
    }
}