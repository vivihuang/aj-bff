package com.askjamie.georgia.message

import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/message")
class MessageController(
        private val messageService: MessageService
) {
    @PostMapping
    fun post(@RequestHeader(required = false) sender: String?, @RequestBody command: SendMessageCommand) =
            messageService.message(sender ?: UUID.randomUUID().toString(), command.message)
}

data class SendMessageCommand(
        val message: String
)

