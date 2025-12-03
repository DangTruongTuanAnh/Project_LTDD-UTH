package com.example.bttheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.bttheme.data.AppTheme
import com.example.bttheme.data.SettingsManager
import com.example.bttheme.ui.screens.AppNavigation
import com.example.bttheme.ui.theme.ThemeSwitcherDemoTheme // Tên theme của bạn

class MainActivity : ComponentActivity() {

    // Khởi tạo SettingsManager
    private val settingsManager by lazy { SettingsManager(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Đọc themeName từ DataStore
            val themeName by settingsManager.themeFlow.collectAsState(initial = AppTheme.LIGHT)

            // Truyền themeName vào Theme
            ThemeSwitcherDemoTheme(themeName = themeName) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Gọi AppNavigation
                    AppNavigation()
                }
            }
        }
    }
}