package com.example.learn.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.learn.MainViewModel
import com.example.learn.R
import com.example.learn.ui.components.AddButton
import com.example.learn.ui.components.Categories
import com.example.learn.ui.components.Heading
import com.example.learn.ui.components.MainButton
import kotlinx.coroutines.launch

@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavHostController) {
    val userWordLists = viewModel.userWordLists.collectAsState().value
    val quizData = viewModel.quizData.collectAsState().value

    val snackBarText = stringResource(R.string.complete)

    LaunchedEffect(Unit) {
        viewModel.fetchUserWordLists()
    }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            SnackbarHost(hostState = snackbarHostState)
            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Heading(stringResource(R.string.words))
                AddButton { navController.navigate("discover") }
            }
            if (userWordLists != null) {
                Categories(userWordLists, navController)
            } else {
                Text("Пусто")
            }
        }
        MainButton("Учить") {
            viewModel.fetchQuizData()
            if (!quizData.isNullOrEmpty()) {
                navController.navigate("quiz/0")
            } else {
                scope.launch { snackbarHostState.showSnackbar(snackBarText) }
            }
        }
    }
}