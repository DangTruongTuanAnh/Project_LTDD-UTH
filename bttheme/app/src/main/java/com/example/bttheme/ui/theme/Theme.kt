package com.example.bttheme.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.bttheme.data.AppTheme // <-- Import

// Bảng màu mặc định
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

// 🔥 TẠO BẢNG MÀU MỚI
private val MagentaColorScheme = darkColorScheme( // Dùng darkColorScheme cho nền tối
    primary = Magenta,
    secondary = LightMagenta,
    tertiary = DarkMagenta,
    background = Color(0xFF3C0028)
)

private val BlueColorScheme = lightColorScheme(
    primary = Blue,
    secondary = LightBlue,
    tertiary = DarkBlue,
    background = Color(0xFFE6F0F8) // Nền xanh nhạt
)

@Composable
fun ThemeSwitcherDemoTheme( // Tên theme của bạn
    // Xóa các tham số cũ, thay bằng themeName
    themeName: String = AppTheme.LIGHT,
    content: @Composable () -> Unit
) {
    // 🔥 CHỌN BẢNG MÀU DỰA TRÊN TÊN
    val colorScheme = when (themeName) {
        AppTheme.MAGENTA -> MagentaColorScheme
        AppTheme.BLUE -> BlueColorScheme
        AppTheme.DARK -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                (themeName == AppTheme.LIGHT || themeName == AppTheme.BLUE) // True nếu nền sáng
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}