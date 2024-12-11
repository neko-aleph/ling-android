package com.example.learn.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.learn.MainViewModel
import com.example.learn.R
import com.example.learn.data.models.Word
import com.example.learn.data.models.WordList
import com.example.learn.ui.components.Heading
import com.example.learn.ui.components.MainButton
import com.example.learn.ui.components.WordItem

@Composable
fun CategoryScreen(viewModel: MainViewModel, id: Int, navController: NavHostController) {
    LaunchedEffect(Unit) {
        viewModel.fetchUserWordLists()
        viewModel.fetchWordLists()
        viewModel.fetchWords(id)
    }

    val wordLists = viewModel.wordLists.collectAsState().value
    val data = viewModel.words.collectAsState().value
    val userWordLists = viewModel.userWordLists.collectAsState().value
    var added: Boolean = true
    if (userWordLists != null) {
        added = userWordLists.any { it.id == id }
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
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                if (wordLists != null) {
                    Heading(wordLists[wordLists.lastIndex-id+1].name)
                }
            }
            LazyColumn(
                modifier = Modifier.height(700.dp).padding(20.dp).clip(RoundedCornerShape(percent = 10)),
                userScrollEnabled = true
            ) {
                if (data != null)
                itemsIndexed(data) { index, item ->
                    WordItem(item)
                    if (index < data.lastIndex) {
                        HorizontalDivider()
                    }
                }
            }
        }
        if (added) {
            //
        } else {
            MainButton(stringResource(R.string.add)) { viewModel.addWordList(id) }
        }
    }
}