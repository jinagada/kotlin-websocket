package org.example.kotlinwebsocket.service

import org.example.kotlinwebsocket.domain.QueueItem
import org.springframework.stereotype.Service

@Service
class QueueService {
    private val queue = mutableListOf<QueueItem>()

    fun addQueueItem(item: QueueItem) {
        queue.add(item)
        // WebSocket 메시지 전송 (아래 코드 참고)
    }

    // ... 다른 메서드들 (수정, 삭제 등)
    fun getQueueItems(): List<QueueItem> {
        return queue
    }
}