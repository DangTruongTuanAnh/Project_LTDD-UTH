package com.example.signinapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.signinapp.R
import com.example.signinapp.viewmodel.ForgotPasswordViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmScreen(navController: NavController, viewModel: ForgotPasswordViewModel) {
    val email by viewModel.email.collectAsState()
    val code by viewModel.code.collectAsState()
    val password by viewModel.password.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(Modifier.height(60.dp))

            // --- Logo ---
            Image(
                painter = painterResource(id = R.drawable.logo_uth),
                contentDescription = "UTH Logo",
                modifier = Modifier
                    .height(80.dp)
                    .width(100.dp)
            )

            Spacer(Modifier.height(20.dp))

            Image(
                painter = painterResource(id = R.drawable.uth_smarttasks),
                contentDescription = "UTH SmartTasks Logo",
                modifier = Modifier
                    .height(100.dp)
                    .width(160.dp)
            )

            Spacer(Modifier.height(32.dp))

            // --- Title ---
            Text(
                text = "Confirm",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "We are here to help you!",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(Modifier.height(24.dp))

            // --- Readonly Fields ---
            OutlinedTextField(
                value = email,
                onValueChange = {},
                label = { Text("Email") },
                singleLine = true,
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = code,
                onValueChange = {},
                label = { Text("Verification Code") },
                singleLine = true,
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {},
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            // --- Button ---
            Button(
                onClick = {
                    // Trở về màn hình đăng nhập hoặc hiển thị thông báo thành công
                    navController.navigate("forgot") {
                        popUpTo("forgot") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF007BFF)
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Summit", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
