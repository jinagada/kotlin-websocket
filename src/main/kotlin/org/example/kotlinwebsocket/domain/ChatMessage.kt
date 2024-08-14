package org.example.kotlinwebsocket.domain

data class ChatMessage(
    var type: MessageType,
    var content: String?,
    var sender: String
)