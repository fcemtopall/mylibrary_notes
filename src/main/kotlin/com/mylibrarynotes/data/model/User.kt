package com.mylibrarynotes.data.model

data class User(
    val email: String,
    val username: String,
    val password: String,
    val hashedPassword: String
)
