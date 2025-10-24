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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.signinapp.R
import com.example.signinapp.viewmodel.ForgotPasswordViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNewPasswordScreen(navController: NavController, viewModel: ForgotPasswordViewModel) {
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }

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
                text = "Create New Password",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Your new password must be different from the old one.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                lineHeight = 20.sp
            )

            Spacer(Modifier.height(24.dp))

            // --- Password Input ---
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    errorMessage = ""
                },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                isError = errorMessage.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    errorMessage = ""
                },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                isError = errorMessage.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            )

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 4.dp)
                )
            }

            Spacer(Modifier.height(24.dp))

            // --- Button ---
            Button(
                onClick = {
                    val pass = password.text.trim()
                    val confirm = confirmPassword.text.trim()

                    when {
                        pass.isEmpty() || confirm.isEmpty() -> {
                            errorMessage = "Please fill in all fields."
                        }
                        pass.length < 6 -> {
                            errorMessage = "Password must be at least 6 characters."
                        }
                        pass != confirm -> {
                            errorMessage = "Passwords do not match."
                        }
                        else -> {
                            errorMessage = ""
                            viewModel.setPassword(pass)
                            navController.navigate("confirm")
                        }
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
                Text("Next", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
