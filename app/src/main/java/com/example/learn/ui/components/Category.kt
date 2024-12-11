package com.example.learn.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.learn.R
import com.example.learn.data.models.WordList

@Composable
fun Category(data: WordList, navController: NavHostController) {
    ListItem(
        headlineContent = {Text(data.name)},
        trailingContent = {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = stringResource(id = R.string.arrow_right_content_desc)
            )
        },
        modifier = Modifier.fillMaxWidth().clickable {navController.navigate("category/${data.id}") }
    )
}