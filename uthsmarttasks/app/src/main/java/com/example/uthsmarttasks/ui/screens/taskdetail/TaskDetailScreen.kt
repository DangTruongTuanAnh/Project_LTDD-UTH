package com.example.uthsmarttasks.ui.screens.taskdetail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Attachment
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Task
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uthsmarttasks.data.model.Task
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uthsmarttasks.ui.theme.LightGrayBackground
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    taskId: String,
    viewModel: TaskDetailViewModel = viewModel(factory = TaskDetailViewModelFactory()),
    onNavigateBack: () -> Unit,
    onDeleteSuccess: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    var showDeleteDialog by remember { mutableStateOf(false) }

    // Lắng nghe sự kiện xóa
    LaunchedEffect(Unit) {
        viewModel.deleteStatus.collectLatest { status ->
            when (status) {
                DeleteStatus.SUCCESS -> {
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show()
                    onDeleteSuccess()
                }
                DeleteStatus.FAILED -> {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show()
                }
                DeleteStatus.IDLE -> {}
            }
        }
    }

    // Lấy chi tiết
    LaunchedEffect(taskId) {
        viewModel.fetchTaskDetail(taskId)
    }

    Scaffold(
        containerColor = Color.White, // Màn hình chi tiết có nền trắng
        topBar = {
            TopAppBar(
                title = { Text("Detail", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Quay lại")
                    }
                },
                actions = {
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(Icons.Default.Delete, contentDescription = "Xóa")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (val state = uiState) {
                is TaskDetailUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is TaskDetailUiState.Success -> {
                    TaskDetailContent(task = state.task)
                }
                is TaskDetailUiState.Error -> {
                    Text(text = "Lỗi: ${state.message}", color = Color.Red)
                }
            }
        }
    }

    // Hộp thoại xác nhận xóa
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Xác nhận xóa") },
            text = { Text("Bạn có chắc chắn muốn xóa công việc này?") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteTask(taskId)
                        showDeleteDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Xóa")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Hủy")
                }
            }
        )
    }
}

@Composable
fun TaskDetailContent(task: Task) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        // Tiêu đề và Mô tả
        item {
            Text(
                text = task.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = task.description,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

        // Box thông tin (Category, Status, Priority)
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(LightGrayBackground)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                InfoChip(
                    icon = Icons.Outlined.Category,
                    label = "Category",
                    value = task.category,
                    iconColor = Color.Blue
                )
                InfoChip(
                    icon = Icons.Outlined.Task,
                    label = "Status",
                    value = task.status,
                    iconColor = Color.Green
                )
                InfoChip(
                    icon = Icons.Outlined.Flag,
                    label = "Priority",
                    value = task.priority,
                    iconColor = Color.Red
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        // Subtasks
        item {
            Text(
                "Subtasks",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        task.subtasks?.let { subtasks ->
            items(subtasks) { subtask ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = subtask.isCompleted, onCheckedChange = null)
                    Text(text = subtask.title)
                }
            }
        }

        // Attachments
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "Attachments",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        task.attachments?.let { attachments ->
            items(attachments) { attachment ->
                Row(
                    // ... (Code của Attachment Item)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Attachment,
                        contentDescription = "Attachment",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = attachment.filename, // Đã đổi tên trong model
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }



        item {
            Spacer(modifier = Modifier.height(24.dp)) // Thêm padding cuối
        }
    }
}

@Composable
fun InfoChip(icon: ImageVector, label: String, value: String, iconColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = iconColor,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}