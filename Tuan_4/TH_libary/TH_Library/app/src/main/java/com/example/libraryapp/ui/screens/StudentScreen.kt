package com.example.libraryapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.libraryapp.data.Student
import com.example.libraryapp.ui.LibraryViewModel
import androidx.navigation.NavHostController

@Composable
fun StudentScreen(navController: NavHostController, viewModel: LibraryViewModel) {
    val students by viewModel.students.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Danh sách Sinh viên", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        if (students.isEmpty()) {
            Text("Chưa có sinh viên nào.")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(students) { student ->
                    StudentCard(
                        student = student,
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun StudentCard(
    student: Student,
    navController: NavHostController,
    viewModel: LibraryViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text("Tên: ${student.name}", style = MaterialTheme.typography.bodyLarge)


            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    // Ghi lại sinh viên hiện tại
                    viewModel.changeStudent(student.name)
                    // Chuyển sang màn hình chọn sách
                    navController.navigate("books")
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Thêm sách")
            }
        }
    }
}
