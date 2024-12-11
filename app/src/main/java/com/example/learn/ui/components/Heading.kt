package com.example.learn.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Heading(text: String) {
    Text(text, style = MaterialTheme.typography.headlineLarge)
}

@Preview
@Composable
fun HeadingPreview() {
    Heading("Слова")
}