package com.example.uthsmarttasks.data.remote

import com.example.uthsmarttasks.data.model.TaskDetailResponse // <-- Import mới
import com.example.uthsmarttasks.data.model.TaskListResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // GET | /tasks
    @GET("tasks")
    suspend fun getAllTasks(): TaskListResponse // Giữ nguyên

    // GET | /task/1
    @GET("task/{id}")
    suspend fun getTaskDetail(@Path("id") taskId: String): TaskDetailResponse // <-- SỬA Ở ĐÂY

    // DEL | /task/1
    @DELETE("task/{id}")
    suspend fun deleteTask(@Path("id") taskId: String): Response<Unit> // Giữ nguyên
}