package com.example.uthsmarttasks.ui.screens.taskdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.uthsmarttasks.data.model.Task
import com.example.uthsmarttasks.data.remote.ApiService
import com.example.uthsmarttasks.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Trạng thái UI cho màn hình chi tiết
sealed interface TaskDetailUiState {
    data object Loading : TaskDetailUiState
    data class Success(val task: Task) : TaskDetailUiState
    data class Error(val message: String) : TaskDetailUiState
}

// Enum cho trạng thái xóa
enum class DeleteStatus {
    IDLE, SUCCESS, FAILED
}

class TaskDetailViewModel(private val apiService: ApiService) : ViewModel() {

    private val _uiState = MutableStateFlow<TaskDetailUiState>(TaskDetailUiState.Loading)
    val uiState: StateFlow<TaskDetailUiState> = _uiState.asStateFlow()

    // Dùng SharedFlow cho sự kiện (xóa thành công/thất bại)
    private val _deleteStatus = MutableSharedFlow<DeleteStatus>()
    val deleteStatus: SharedFlow<DeleteStatus> = _deleteStatus.asSharedFlow()

    fun fetchTaskDetail(taskId: String) {
        viewModelScope.launch {
            _uiState.value = TaskDetailUiState.Loading
            try {
                // SỬA LOGIC KIỂM TRA
                val response = apiService.getTaskDetail(taskId)
                if (response.isSuccess && response.data != null) {
                    _uiState.value = TaskDetailUiState.Success(response.data)
                } else {
                    _uiState.value = TaskDetailUiState.Error(response.message)
                }
            } catch (e: Exception) {
                _uiState.value = TaskDetailUiState.Error(e.message ?: "Lỗi không xác định")
            }
        }
    }

    fun deleteTask(taskId: String) {
        viewModelScope.launch {
            try {
                val response = apiService.deleteTask(taskId)
                if (response.isSuccessful) {
                    _deleteStatus.emit(DeleteStatus.SUCCESS)
                } else {
                    _deleteStatus.emit(DeleteStatus.FAILED)
                }
            } catch (e: Exception) {
                _deleteStatus.emit(DeleteStatus.FAILED)
            }
        }
    }
}

// Factory
class TaskDetailViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskDetailViewModel(RetrofitInstance.api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}