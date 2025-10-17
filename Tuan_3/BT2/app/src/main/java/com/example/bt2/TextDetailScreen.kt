package com.example.bt2

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material.icons.automirrored.filled.ArrowBack


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextDetailScreen(navController: NavController) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Text Detail",
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color(0xFF1E88E5),
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color (0xFF1E88E5)
                        )
                    }
                }
            )
        }
    ) { innerPadding -> // 🟢 sử dụng innerPadding ở dưới
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // tránh trùng top bar
                .padding(24.dp)
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            // 🟩 NỘI DUNG (ở giữa màn hình)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 40.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Văn bản có nhiều style
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 40.sp)) {
                            append("The ")
                        }
                        withStyle(
                            style = SpanStyle(
                                textDecoration = TextDecoration.LineThrough,
                                fontSize = 40.sp
                            )
                        ) {
                            append("quick ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFFBF7C2C),
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Brown\n")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                letterSpacing = 4.sp,
                                fontWeight = FontWeight.Medium,
                                fontSize = 40.sp
                            )
                        ) {
                            append("fox jumps ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 40.sp
                            )
                        ) {
                            append("over\n")
                        }
                        withStyle(
                            style = SpanStyle(
                                textDecoration = TextDecoration.Underline,
                                fontSize = 40.sp
                            )
                        ) {
                            append("the ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                fontSize = 40.sp
                            )
                        ) {
                            append("lazy ")
                        }
                        withStyle(style = SpanStyle(fontSize = 40.sp)) {
                            append("Dog.")
                        }
                    },
                    lineHeight = 50.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
