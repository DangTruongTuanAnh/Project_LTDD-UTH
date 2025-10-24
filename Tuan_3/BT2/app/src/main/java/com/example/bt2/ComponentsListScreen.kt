package com.example.bt2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ComponentsListScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Tiêu đề
        Text(
            text = "UI Components List",
            fontSize = 20.sp,
            color = Color(0xFF1E88E5),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mục Display
        SectionTitle("Display")
        ComponentCard(title = "Text", desc = "Displays text", navController = navController)
        ComponentCard(title = "Image", desc = "Displays an image", navController = navController)

        // Mục Input
        SectionTitle("Input")
        ComponentCard(title = "TextField", desc = "Input field for text", navController = navController)
        ComponentCard(title = "PasswordField", desc = "Input field for passwords", navController = navController)

        // Mục Layout
        SectionTitle("Layout")
        ComponentCard(title = "Column", desc = "Arranges elements vertically", navController = navController)
        ComponentCard(title = "Row", desc = "Arranges elements horizontally", navController = navController)
        SectionTitle("Column1/Cloumnlazy")
        ComponentCard(title = "Column1", desc = "Arranges elements vertically", navController = navController)
        ComponentCard(title = "Cloumnlazy", desc = "Arranges elements horizontally", navController = navController)
    }
}

// Tiêu đề nhóm (Display, Input, Layout)
@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black,
        modifier = Modifier
            .padding(vertical = 8.dp)
    )
}

// Thẻ hiển thị mỗi component
@Composable
fun ComponentCard(
    title: String,
    desc: String,
    backgroundColor: Color = Color(0xFFBBDEFB),
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .padding(12.dp)
            .clickable {
                when (title) {
                    "Text" -> navController.navigate("textDetailScreen")
                    "Image" -> navController.navigate("imageDetailScreen")
                    "TextField" -> navController.navigate("textFieldDetailScreen")
                    "PasswordField" -> navController.navigate("passwordFieldScreen")
                    "Row" -> navController.navigate("rowLayoutScreen")
                    "Column" -> navController.navigate("columnLayoutScreen")
                    "Cloumnlazy" -> navController.navigate("componentsNew")
                    "Column1" -> navController.navigate("column1")
                }
            }
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )
        Text(
            text = desc,
            fontSize = 14.sp,
            color = Color.DarkGray
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}
