package com.askjamie.georgia.message

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("api/message")
class MessageController(
        private val messageService: MessageService
) {
    @PostMapping
    fun post(@RequestBody command: SendMessageCommand) =
            messageService.message(command.sender ?: UUID.randomUUID().toString(), command.message)
}

data class SendMessageCommand(
        val sender: String? = null,
        val message: String
)
