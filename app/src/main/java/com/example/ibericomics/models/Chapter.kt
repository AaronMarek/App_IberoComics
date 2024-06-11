package com.example.ibericomics.models

data class Chapter(
    val id: Int? = null,
    val title: String,
    val chapterNumber: Int,
    val comicId: Int
)
