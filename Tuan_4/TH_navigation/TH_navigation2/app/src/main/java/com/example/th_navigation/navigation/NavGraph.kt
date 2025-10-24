package com.example.th_navigation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.th_navigation.ui.screen.*

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { Screen1(navController) }
        composable("onboard1") { Screen2(navController) }
        composable("onboard2") { Screen3(navController) }
        composable("onboard3") { Screen4(navController) }
    }
}
