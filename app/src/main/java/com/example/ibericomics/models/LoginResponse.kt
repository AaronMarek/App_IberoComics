package com.example.ibericomics.models
data class LoginResponse(
    val token: String,
    val success: Boolean,
    val user: User
)
