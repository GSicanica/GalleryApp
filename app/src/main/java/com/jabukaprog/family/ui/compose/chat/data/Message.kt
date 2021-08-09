package com.jabukaprog.family.ui.compose.chat.data

data class Message(
    val user: User,
    val text: String,
    val imageUrl: String? = null,
)
