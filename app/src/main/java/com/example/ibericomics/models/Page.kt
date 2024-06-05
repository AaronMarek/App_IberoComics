package com.example.ibericomics.models

data class Page(
    val id: Int? = null,
    val pageNumber: Int,
    val imagePath: String,
    val chapterId: Int
)
