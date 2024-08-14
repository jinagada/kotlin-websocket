package org.example.kotlinwebsocket.domain

import java.time.LocalDateTime

data class QueueItem(
    val id: Long,
    val status: String,
    val createdTime: LocalDateTime
)
