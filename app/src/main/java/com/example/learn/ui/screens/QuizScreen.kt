package com.example.learn.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.learn.MainViewModel
import com.example.learn.data.models.Result
import com.example.learn.ui.components.AnswerAlert
import com.example.learn.ui.components.QuizButton
import com.example.learn.ui.components.QuizWord
import kotlin.random.Random

@Composable
fun QuizScreen(viewModel: MainViewModel, id: Int, navController: NavHostController) {
    val quizData = viewModel.quizData.collectAsState().value

    var correct by remember { mutableStateOf(0) }
    var answers by remember { mutableStateOf(mutableListOf(0, 0, 0, 0)) }

    LaunchedEffect(Unit) {
        if(id == 0) {
            viewModel.fetchQuizData()
        }
        correct = Random.nextInt(0, 4)
        answers = generateUniqueIntegers(id)
        answers.add(correct, id)
    }

    var answerGiven by remember { mutableStateOf(false) }
    var answerCorrect by remember { mutableStateOf(false) }

    fun giveAnswer(correct: Boolean) {
        if (quizData != null) {
            answerCorrect = correct
            answerGiven = true
            viewModel.results.add(Result(quizData[id].id, correct))
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = { navController.navigate("main") }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Text("${id+1}/10", style = MaterialTheme.typography.bodyLarge)
            }
            if (quizData != null) {
                QuizWord(quizData[id].original)

                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    QuizButton(quizData[answers[0]].translation) { giveAnswer(correct==0) }
                    QuizButton(quizData[answers[1]].translation) { giveAnswer(correct==1) }
                }
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    QuizButton(quizData[answers[2]].translation) { giveAnswer(correct==2) }
                    QuizButton(quizData[answers[3]].translation) { giveAnswer(correct==3) }
                }
            }
        }
    }
    if (answerGiven) {
        Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) { AnswerAlert(answerCorrect, id+1, navController, viewModel) }
    }
}

fun generateUniqueIntegers(exclude: Int): MutableList<Int> {
    val allNumbers = (0..9).toList().filter { it != exclude }
    return allNumbers.shuffled().subList(0, 3).toMutableList()
}