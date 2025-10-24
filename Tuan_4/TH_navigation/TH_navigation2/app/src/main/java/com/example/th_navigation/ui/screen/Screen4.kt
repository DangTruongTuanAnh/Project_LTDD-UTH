package com.example.th_navigation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.navigation.NavController
import com.example.th_navigation.R

@Composable
fun Screen4(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        // Hàng trên cùng: 3 chấm indicator + nút Skip
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 3 chấm tròn
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Screen2Components.IndicatorDot(isActive = false)
                Screen2Components.IndicatorDot(isActive = false)
                Screen2Components.IndicatorDot(isActive = true)
            }

            // Nút Skip
            Text(
                text = "Skip",
                color = Color(0xFF007BFF),
                modifier = Modifier
                    .clickable {
                        navController.navigate("splash") {
                            popUpTo("onboard1") { inclusive = true }
                        }
                    },
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(Modifier.height(12.dp))

        // Hình minh họa
        Image(
            painter = painterResource(id = R.drawable.img_screen_2),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        )

        // Tiêu đề
        Text(
            text = "Increase Work Effectiveness",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(8.dp))

        // Mô tả
        Text(
            text = "Time management and the determination of more important tasks will give your job statistics better and always improve.",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp),
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(Modifier.height(16.dp))

        // Hàng nút Back và Next
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Nút Back (tròn, icon to hơn)
            Box(
                modifier = Modifier
                    .size(56.dp) // to hơn, bo tròn đẹp
                    .background(Color(0xFFE0E0E0), shape = CircleShape)
                    .clickable { navController.navigate("onboard2") },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "Back",
                    tint = Color(0xFF007BFF),
                    modifier = Modifier.size(28.dp) // icon to rõ
                )
            }

            // Nút Next (xanh đậm, bo tròn)
            Button(
                onClick = { navController.navigate("splash") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF007BFF),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
                    .height(56.dp)
            ) {
                Text(
                    "Next",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
