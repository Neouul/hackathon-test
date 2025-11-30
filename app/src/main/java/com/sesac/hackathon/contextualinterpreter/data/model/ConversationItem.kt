package com.sesac.hackathon.contextualinterpreter.data.model

enum class Emotion {
    NEUTRAL, HAPPY, ANGRY, SAD, SURPRISED, FEAR
}

data class ConversationItem(
    val id: Long,
    val speaker: String,
    val text: String,
    val emotion: Emotion,
    val timestamp: Long,
    val isUser: Boolean = false
)
