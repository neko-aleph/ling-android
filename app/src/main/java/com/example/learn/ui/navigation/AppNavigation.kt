package com.example.learn.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.learn.MainViewModel
import com.example.learn.ui.screens.CategoryScreen
import com.example.learn.ui.screens.DiscoverScreen
import com.example.learn.ui.screens.MainScreen
import com.example.learn.ui.screens.QuizScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val vm: MainViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen (vm, navController)
        }
        composable(route = "category/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType })) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 1
            CategoryScreen(vm, id, navController)
        }
        composable("discover") {
            DiscoverScreen(vm, navController)
        }
        composable("quiz/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType })) {backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            QuizScreen(vm, id, navController)
        }
    }
}
