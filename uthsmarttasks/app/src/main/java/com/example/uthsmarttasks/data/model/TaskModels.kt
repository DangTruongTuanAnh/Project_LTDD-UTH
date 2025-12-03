package com.example.uthsmarttasks.data.model

import com.google.gson.annotations.SerializedName

// Model cho đối tượng Task (chi tiết)
data class Task(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("status") val status: String,
    @SerializedName("priority") val priority: String,
    @SerializedName("category") val category: String,
    @SerializedName("dueDate") val dueDate: String,
    @SerializedName("subtasks") val subtasks: List<Subtask>?,
    @SerializedName("attachments") val attachments: List<Attachment>?
)

// Model cho Subtask
data class Subtask(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("isCompleted") val isCompleted: Boolean
)

// Model cho Attachment
data class Attachment(
    @SerializedName("id") val id: Int,
    @SerializedName("fileName") val filename: String, // Đổi tên cho khớp code cũ
    @SerializedName("fileUrl") val fileUrl: String
)

// --- CÁC RESPONSE WRAPPER ---

// Model cho response của Màn hình List (GET /tasks)
data class TaskListResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<Task>? // Danh sách Task
)

// Model cho response của Màn hình Detail (GET /task/{id})
data class TaskDetailResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Task? // Một Task
)