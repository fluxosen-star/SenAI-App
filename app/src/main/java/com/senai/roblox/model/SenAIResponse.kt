package com.senai.roblox.model

data class SenAIResponse(
    val code: String = "",
    val explanation: String? = null,
    val hasErrors: Boolean = false,
    val errors: List<String> = emptyList(),
    val optimizations: List<String> = emptyList(),
    val canObfuscate: Boolean = true
)

data class SenAIRequest(
    val userMessage: String,
    val conversationHistory: List<ConversationItem> = emptyList()
)

data class ConversationItem(
    val role: String, // "user" or "assistant"
    val content: String
)
