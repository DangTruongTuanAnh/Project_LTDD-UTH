package com.example.th_navigation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.th_navigation.R

@Composable
fun Screen1(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("onboard1") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo hình biểu tượng
            Image(
                painter = painterResource(id = R.drawable.logo_uth),
                contentDescription = null,
                modifier = Modifier.size(180.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Logo chữ "UTH SmartTasks" (thay cho contentDescription)
            Image(
                painter = painterResource(id = R.drawable.uth_smarttasks),
                contentDescription = null,
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(0.7f) // chiều rộng 70% màn hình
            )
        }
    }
}
