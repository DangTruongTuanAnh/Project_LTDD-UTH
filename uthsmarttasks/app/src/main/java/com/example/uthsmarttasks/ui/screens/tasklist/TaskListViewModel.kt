package com.example.uthsmarttasks.ui.screens.tasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.uthsmarttasks.data.model.Task
import com.example.uthsmarttasks.data.remote.ApiService
import com.example.uthsmarttasks.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Trạng thái UI
sealed interface TaskListUiState {
    data object Loading : TaskListUiState
    data object Empty : TaskListUiState
    data class Success(val tasks: List<Task>) : TaskListUiState
    data class Error(val message: String) : TaskListUiState
}

class TaskListViewModel(private val apiService: ApiService) : ViewModel() {

    private val _uiState = MutableStateFlow<TaskListUiState>(TaskListUiState.Loading)
    val uiState: StateFlow<TaskListUiState> = _uiState.asStateFlow()

    init {
        fetchTasks()
    }

    fun fetchTasks() {
        viewModelScope.launch {
            _uiState.value = TaskListUiState.Loading
            try {
                val response = apiService.getAllTasks()

                // SỬA LOGIC KIỂM TRA
                if (response.isSuccess && response.data != null) {
                    val tasksList = response.data
                    if (tasksList.isEmpty()) {
                        _uiState.value = TaskListUiState.Empty
                    } else {
                        _uiState.value = TaskListUiState.Success(tasksList)
                    }
                } else {
                    // API trả về isSuccess = false hoặc data = null
                    _uiState.value = TaskListUiState.Error(response.message)
                }
            } catch (e: Exception) {
                _uiState.value = TaskListUiState.Error(e.message ?: "Lỗi không xác định")
            }
        }
    }
}

// 🔥 HÃY ĐẢM BẢO BẠN CÓ ĐOẠN CODE NÀY Ở CUỐI FILE
class TaskListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskListViewModel(RetrofitInstance.api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}