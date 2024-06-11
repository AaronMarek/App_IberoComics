package com.example.ibericomics.models

data class User(
    val id: Long? = null,
    val username: String,
    var password: String,
    val fullName: String,
    val email: String
)
