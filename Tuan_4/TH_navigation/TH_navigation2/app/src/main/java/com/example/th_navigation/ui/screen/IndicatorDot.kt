package com.example.th_navigation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object Screen2Components {
    @Composable
    fun IndicatorDot(isActive: Boolean) {
        Box(
            modifier = Modifier
                .size(if (isActive) 16.dp else 12.dp) // chấm to rõ hơn
                .background(
                    if (isActive) Color(0xFF007BFF) else Color(0xFFD0D0D0),
                    shape = CircleShape
                )
        )
    }
}
