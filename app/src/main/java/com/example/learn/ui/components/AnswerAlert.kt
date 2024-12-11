package com.example.learn.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.learn.MainViewModel
import com.example.learn.R

@Composable
fun AnswerAlert(correct: Boolean, nextId: Int, navController: NavHostController, viewModel: MainViewModel) {
    val bgcolor: Color
    if (correct) {
        bgcolor = MaterialTheme.colorScheme.primaryContainer
    } else {
        bgcolor = MaterialTheme.colorScheme.errorContainer
    }
    Card(Modifier.fillMaxWidth().height(200.dp).background(bgcolor)) {
        Column (verticalArrangement = Arrangement.SpaceBetween){
            if (correct) {
                Text(
                    stringResource(R.string.correct),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(20.dp)
                )
            } else {
                Text(
                    stringResource(R.string.incorrect),
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(20.dp)
                )
            }
            if (nextId < 10) {
                MainButton(stringResource(R.string.next)) { navController.navigate("quiz/$nextId") }
            } else {
                MainButton(stringResource(R.string.finish)) {
                    viewModel.sendResults()
                    navController.navigate("main")
                }
            }
        }
    }
}