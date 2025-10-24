package com.example.libraryapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.libraryapp.data.Book
import com.example.libraryapp.ui.LibraryViewModel

@Composable
fun BookListScreen(
    navController: NavController,
    viewModel: LibraryViewModel
) {
    val students by viewModel.students.collectAsState()
    val currentStudent by viewModel.currentStudent.collectAsState()

    if (currentStudent == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Chưa chọn sinh viên nào.")
        }
        return
    }

    val borrowedBooks = currentStudent!!.borrowedBooks
    val allBooks = students.flatMap { it.borrowedBooks }.distinctBy { it.id }
    val availableBooks = listOf(
        Book(1, "Kotlin cơ bản"),
        Book(2, "Android nâng cao"),
        Book(3, "Lập trình Compose"),
        Book(4, "Cơ sở dữ liệu SQL"),
        Book(5, "Clean Code")
    ).filterNot { b -> borrowedBooks.any { it.id == b.id } }

    var selectedBooks by remember { mutableStateOf(setOf<Book>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Chọn sách để mượn", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        if (availableBooks.isEmpty()) {
            Text("Tất cả sách đã được mượn.", color = MaterialTheme.colorScheme.error)
        } else {
            LazyColumn {
                items(availableBooks) { book ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(book.title)
                            Checkbox(
                                checked = selectedBooks.contains(book),
                                onCheckedChange = { checked ->
                                    selectedBooks = if (checked) {
                                        selectedBooks + book
                                    } else {
                                        selectedBooks - book
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                selectedBooks.forEach { viewModel.addBookToCurrentStudent(it) }
                navController.navigate("manage")  // quay lại màn chính
            },
            enabled = selectedBooks.isNotEmpty()
        ) {
            Text("Xác nhận mượn")
        }
    }
}
