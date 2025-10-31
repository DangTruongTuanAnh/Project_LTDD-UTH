package com.example.btfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.btfirebase.navigation.AppNavigation
import com.example.btfirebase.ui.theme.BtFirebaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BtFirebaseTheme {
                AppNavigation()
            }
        }
    }
}
