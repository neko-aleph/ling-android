package com.example.learn.data.models

data class Word (
    val id: Int,
    val original: String,
    val translation: String,
    var learned: Int
)