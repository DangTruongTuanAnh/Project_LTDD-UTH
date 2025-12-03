package com.example.uthsmarttasks.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uthsmarttasks.ui.screens.taskdetail.TaskDetailScreen
import com.example.uthsmarttasks.ui.screens.tasklist.TaskListScreen

// Định nghĩa các route
object AppRoutes {
    const val TASK_LIST = "taskList"
    const val TASK_DETAIL = "taskDetail"
    const val TASK_DETAIL_ARG_ID = "taskId"
    const val TASK_DETAIL_ROUTE = "$TASK_DETAIL/{$TASK_DETAIL_ARG_ID}"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRoutes.TASK_LIST) {

        // Màn hình Danh sách
        composable(route = AppRoutes.TASK_LIST) { navBackStackEntry ->

            // 1. Lắng nghe kết quả trả về
            val needsRefresh = navBackStackEntry.savedStateHandle.get<Boolean>("refresh_list")

            TaskListScreen(
                onTaskClick = { taskId ->
                    navController.navigate("${AppRoutes.TASK_DETAIL}/$taskId")
                },
                // 2. Thêm 2 tham số mới này
                needsRefresh = needsRefresh == true,
                onRefreshComplete = {
                    // Reset cờ refresh
                    navBackStackEntry.savedStateHandle["refresh_list"] = false
                }
            )
        }

        // Màn hình Chi tiết
        composable(
            route = AppRoutes.TASK_DETAIL_ROUTE,
            arguments = listOf(navArgument(AppRoutes.TASK_DETAIL_ARG_ID) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString(AppRoutes.TASK_DETAIL_ARG_ID)

            if (taskId != null) {
                TaskDetailScreen(
                    taskId = taskId,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    // 3. Thêm tham số mới này
                    onDeleteSuccess = {
                        // Đặt kết quả cho màn hình trước đó
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("refresh_list", true)
                        // Quay lại
                        navController.popBackStack()
                    }
                )
            } else {
                navController.popBackStack()
            }
        }
    }
}