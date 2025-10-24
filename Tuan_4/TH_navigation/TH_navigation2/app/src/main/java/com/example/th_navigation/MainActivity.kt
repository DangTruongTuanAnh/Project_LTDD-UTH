package com.example.th_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.th_navigation.navigation.NavGraph
import com.example.th_navigation.ui.theme.UTHSmartTasksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UTHSmartTasksTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
