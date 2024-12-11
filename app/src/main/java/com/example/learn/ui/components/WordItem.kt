package com.example.learn.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.learn.data.models.NewWord

@Composable
fun WordItem(data: NewWord) {
    ListItem(
        headlineContent = {Text(data.original)},
        supportingContent = {Text(data.translation)},
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun WordItemPreview() {
    val sample = NewWord(1, "Apple", "Яблоко")
    WordItem(sample)
}