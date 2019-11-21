package com.askjamie.georgia.message

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("api/message")
class MessageController(
        private val messageService: MessageService
) {
    @PostMapping("rasa")
    fun postToRasa(@RequestBody command: SendMessageCommand) =
            messageService.messageToRasa(command.sender ?: UUID.randomUUID().toString(), command.message)

    @PostMapping("dialog-flow")
    fun postToDialogFlow(@RequestBody command: SendMessageCommand) =
            messageService.messageToDialogFlow(command.sender ?: UUID.randomUUID().toString(), command.message)
}

data class SendMessageCommand(
        val sender: String? = null,
        val message: String
)
