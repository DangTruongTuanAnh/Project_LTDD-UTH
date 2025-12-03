package com.example.uthsmarttasks.ui.screens.tasklist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AssignmentLate
import androidx.compose.material.icons.outlined.EventNote
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ListAlt
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Task
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uthsmarttasks.R
// 🔥 SỬA IMPORT: Dùng model 'Task' mới
import com.example.uthsmarttasks.data.model.Task
import com.example.uthsmarttasks.ui.theme.*
import androidx.compose.runtime.LaunchedEffect

// Hàm helper để lấy màu dựa trên category
@Composable
private fun getTaskColors(category: String): Pair<Color, Color> {
    // Cập nhật các case cho khớp với JSON mới của bạn
    return when (category.lowercase()) {
        "work" -> TaskPink to TaskPinkText
        "personal" -> TaskGreen to TaskGreenText
        "fitness" -> TaskBlue to TaskBlueText
        "health" -> TaskGreen to TaskGreenText
        "shopping" -> TaskPink to TaskPinkText
        "education" -> TaskBlue to TaskBlueText
        // Thêm các category khác nếu muốn
        else -> LightGrayBackground to Color.Black
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    viewModel: TaskListViewModel = viewModel(factory = TaskListViewModelFactory()),
    onTaskClick: (String) -> Unit,
    needsRefresh: Boolean,
    onRefreshComplete: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
// Tự động tải lại danh sách khi cờ 'needsRefresh' là true
    LaunchedEffect(needsRefresh) {
        if (needsRefresh) {
            viewModel.fetchTasks()
            onRefreshComplete()
        }
    }
    Scaffold(
        containerColor = LightGrayBackground, // Màu nền chung
        topBar = {
            HomeTopBar() // TopBar mới
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Xử lý thêm mới */ },
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = "Thêm công việc")
            }
        },
        bottomBar = {
            HomeBottomBar() // BottomBar mới
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (val state = uiState) {
                is TaskListUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is TaskListUiState.Empty -> {
                    EmptyView()
                }
                is TaskListUiState.Success -> {
                    TaskList(tasks = state.tasks, onTaskClick = onTaskClick)
                }
                is TaskListUiState.Error -> {
                    Text(text = "Lỗi: ${state.message}", color = Color.Red)
                }
            }
        }
    }
}

@Composable
fun HomeTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE6F0F8)) // Màu xanh nhạt của TopBar
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Bạn cần thêm file logo 'logo_uth.png' vào thư mục res/drawable
        Image(
            painter = painterResource(id = R.drawable.logo_uth),
            contentDescription = "UTH Logo",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Column(modifier = Modifier.padding(horizontal = 12.dp).weight(1f)) {
            Text(
                text = "SmartTasks",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1), // Màu xanh đậm
                fontSize = 18.sp
            )
            Text(
                text = "A simple and efficient to-do app",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }

        // Bạn cần thêm logo 'chuong.png' vào res/drawable
        Image(
            painter = painterResource(id = R.drawable.chuong),
            contentDescription = "App Icon",
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun HomeBottomBar() {
    BottomAppBar(
        containerColor = Color.White,
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomBarIcon(icon = Icons.Outlined.Home, text = "Home")
                BottomBarIcon(icon = Icons.Outlined.EventNote, text = "Calendar")

                // Spacer để chừa chỗ cho FAB
                Spacer(modifier = Modifier.width(56.dp))

                BottomBarIcon(icon = Icons.Outlined.ListAlt, text = "List")
                BottomBarIcon(icon = Icons.Outlined.Settings, text = "Settings")
            }
        }
    )
}

@Composable
fun BottomBarIcon(icon: ImageVector, text: String, isSelected: Boolean = false) {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
        )
    }
}


@Composable
fun TaskList(tasks: List<Task>, onTaskClick: (String) -> Unit) { // 🔥 SỬA MODEL
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(tasks) { task ->
            // 🔥 SỬA LOGIC CLICK: Chuyển Int thành String
            TaskItem(task = task, onClick = { onTaskClick(task.id.toString()) })
        }
    }
}

@Composable
fun TaskItem(task: Task, onClick: () -> Unit) { // 🔥 SỬA MODEL
    val (backgroundColor, textColor) = getTaskColors(task.category)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Checkbox(
                checked = task.status == "Completed",
                onCheckedChange = null,
                colors = CheckboxDefaults.colors(
                    checkmarkColor = Color.White,
                    checkedColor = textColor,
                    uncheckedColor = textColor
                )
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = task.description,
                    color = textColor.copy(alpha = 0.8f),
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Status: ${task.status}",
                        color = textColor.copy(alpha = 0.8f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = task.dueDate, // Bạn có thể cần format lại ngày tháng này
                        color = textColor.copy(alpha = 0.8f),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp), // Thêm padding ngang
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 1. Thêm Box xám bo góc
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f) // Giữ cho Box vuông
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFEEEEEE)), // Màu xám nhạt
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sleepy_clipboard_icon),
                    contentDescription = "No Tasks",
                    modifier = Modifier.size(120.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // 3. Thêm Text
                Text(
                    text = "No Tasks Yet!",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Stay productive—add something to do",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}