package com.example.learn.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.learn.data.models.WordList

@Composable
fun Categories(data: List<WordList>, navController: NavHostController) {
    LazyColumn (
        modifier = Modifier.fillMaxWidth().padding(20.dp).clip(RoundedCornerShape(percent = 10))
    ) {
        itemsIndexed(data){ index, item ->
            Category(item, navController)
            if (index < data.lastIndex) {
                HorizontalDivider()
            }
        }
    }
}