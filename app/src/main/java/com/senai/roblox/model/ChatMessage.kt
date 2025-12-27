package com.senai.roblox.model

enum class MessageType {
    SENT,
    RECEIVED
}

data class ChatMessage(
    val content: String,
    val type: MessageType,
    val isCode: Boolean = false,
    val timestamp: Long = System.currentTimeMillis(),
    val isTyping: Boolean = false,
    val canObfuscate: Boolean = false
)
