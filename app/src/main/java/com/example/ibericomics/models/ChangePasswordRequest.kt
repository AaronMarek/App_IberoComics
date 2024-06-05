package com.example.ibericomics.models

data class ChangePasswordRequest(
    val userId: Int,
    val oldPassword: String,
    val newPassword: String
)
