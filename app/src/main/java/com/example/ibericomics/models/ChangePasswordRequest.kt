package com.example.ibericomics.models

data class ChangePasswordRequest(
    val userId: Long,
    val oldPassword: String,
    val newPassword: String
)
