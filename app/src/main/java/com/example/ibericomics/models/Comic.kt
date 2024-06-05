package com.example.ibericomics.models

data class Comic(
    val id: Int? = null,
    val title: String,
    val author: String,
    val coverUrl: String,
    val userId: Int
)
