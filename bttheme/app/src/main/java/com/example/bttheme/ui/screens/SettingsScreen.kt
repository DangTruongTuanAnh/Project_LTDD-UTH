package com.example.bttheme.ui.screens

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bttheme.data.SettingsManager
import kotlinx.coroutines.launch
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bttheme.data.AppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

// Bạn có thể đặt ViewModel và Screen trong cùng file cho demo
class SettingsViewModel(private val settingsManager: SettingsManager) : ViewModel() {
    val themeFlow = settingsManager.themeFlow
    fun saveTheme(themeName: String) {
        viewModelScope.launch {
            settingsManager.saveTheme(themeName)
        }
    }
}

class SettingsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(SettingsManager(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

// ----- UI SCREEN -----
// (Khối import đã bị xóa khỏi đây)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    viewModel: SettingsViewModel = viewModel(factory = SettingsViewModelFactory(LocalContext.current.applicationContext))
) {
    val currentTheme by viewModel.themeFlow.collectAsState(initial = AppTheme.LIGHT)
    var selectedTheme by remember(currentTheme) { mutableStateOf(currentTheme) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Setting") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Choosing the right theme sets the tone...",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ThemeColorChip(
                    color = Color(0xFF0095FF), // Blue
                    isSelected = selectedTheme == AppTheme.BLUE,
                    onClick = { selectedTheme = AppTheme.BLUE }
                )
                ThemeColorChip(
                    color = Color(0xFFFF00A6), // Magenta
                    isSelected = selectedTheme == AppTheme.MAGENTA,
                    onClick = { selectedTheme = AppTheme.MAGENTA }
                )
                ThemeColorChip(
                    color = Color.Black, // Dark
                    isSelected = selectedTheme == AppTheme.DARK,
                    onClick = { selectedTheme = AppTheme.DARK }
                )
                ThemeColorChip(
                    color = Color.White, // Light
                    isSelected = selectedTheme == AppTheme.LIGHT,
                    onClick = { selectedTheme = AppTheme.LIGHT }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { viewModel.saveTheme(selectedTheme) },
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Apply", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun ThemeColorChip(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val outlineColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray
    val chipColor = if (color == Color.White) Color.White else color
    val iconColor = if (color == Color.White) Color.Black else Color.White

    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(chipColor)
            .border(4.dp, outlineColor, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = iconColor,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}