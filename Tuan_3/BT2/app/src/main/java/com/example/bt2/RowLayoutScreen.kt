package com.example.bt2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowLayoutScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Row Layout",
                        color = Color(0xFF1E88E5),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF1E88E5)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        RowLayoutContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun RowLayoutContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(4) {
            // Container bo góc, nền xám nhạt bao quanh mỗi Row
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF7F8FA), RoundedCornerShape(16.dp))
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(width = 90.dp, height = 50.dp)
                            .background(Color(0xFF90CAF9), RoundedCornerShape(12.dp))
                    )
                    Box(
                        modifier = Modifier
                            .size(width = 90.dp, height = 50.dp)
                            .background(Color(0xFF42A5F5), RoundedCornerShape(12.dp))
                    )
                    Box(
                        modifier = Modifier
                            .size(width = 90.dp, height = 50.dp)
                            .background(Color(0xFF90CAF9), RoundedCornerShape(12.dp))
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
