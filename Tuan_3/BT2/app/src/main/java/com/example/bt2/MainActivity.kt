package com.example.bt2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "welcome") {
                    composable("welcome") { WelcomeScreen(navController) }
                    composable("components") { ComponentsListScreen(navController) }
                    composable("textDetailScreen") { TextDetailScreen(navController) }
                    composable("imageDetailScreen") { ImageDetailScreen(navController) }
                    composable("textFieldDetailScreen") { TextFieldDetailScreen(navController) }
                    composable("passwordFieldScreen") { PasswordFieldScreen(navController) }
                    composable("rowLayoutScreen") { RowLayoutScreen(navController) }
                    composable("columnLayoutScreen") { ColumnLayoutScreen(navController) }

                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Hàng chứa tên và số điện thoại ở góc trên bên phải
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "Đặng Trương Tuấn Anh",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "080205012263",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        // Logo Jetpack Compose
        Image(
            painter = painterResource(id = R.drawable.image1),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .padding(top = 50.dp)
        )

        // Phần mô tả
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Jetpack Compose",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp ,
                modifier = Modifier.padding(top = 50.dp)
            )
            Text(
                text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 30.dp, bottom = 50.dp)
            )
        }

        // Nút bấm “I’m ready”
        Button(
            onClick = { navController.navigate("components") },
            modifier = Modifier
                .padding(top =  70.dp)
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = "I’m ready",
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}
