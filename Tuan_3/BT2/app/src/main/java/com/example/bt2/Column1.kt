package com.example.bt2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ColumnScreen(navController: NavController) {
    val itemsList = List(10_000) { index -> "Item #$index" }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        itemsList.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image1),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Item nặng: $item",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
            }

            // Thêm Divider cho mỗi item => tăng thêm chi phí vẽ
            HorizontalDivider()
        }
    }
}
