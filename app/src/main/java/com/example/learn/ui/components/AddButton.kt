package com.example.learn.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learn.R

@Composable
fun AddButton(onClick: () -> Unit) {
    IconButton(
        onClick,
        modifier = Modifier
            .then(Modifier.size(50.dp))
    ) {
        Icon(Icons.Default.Add, contentDescription = stringResource(R.string.plus_content_desc))
    }
}

@Preview
@Composable
fun AddButtonPreview() {
    AddButton {  }
}