package org.example.kotlinwebsocket.controller

import org.apache.logging.log4j.message.SimpleMessage
import org.example.kotlinwebsocket.domain.QueueItem
import org.example.kotlinwebsocket.service.QueueService
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.time.LocalDateTime

@Controller
class WebSocketController(private val queueService: QueueService, private val messagingTemplate: SimpMessagingTemplate) {
    private val logger = LoggerFactory.getLogger(WebSocketController::class.java)

    @MessageMapping("/queue")
    fun handleQueueMessage(message: SimpleMessage) {
        logger.info("Received message {}", message)
        val size = queueService.getQueueItems().size
        // 메시지 처리 로직 (예: 대기열 항목 추가)
        queueService.addQueueItem(QueueItem(size + 1L, "WAITING", LocalDateTime.now()))
        // 모든 연결된 클라이언트에게 메시지 전송
        val sendData = if (size < 1) null else queueService.getQueueItems()[size - 1]
        sendData?.let { messagingTemplate.convertAndSend("/topic/queue", it) }
    }

    @GetMapping("/queue")
    fun getQueueItems(model: Model): String {
        model.addAttribute("queueItems", queueService.getQueueItems())
        return "queue"
    }
}