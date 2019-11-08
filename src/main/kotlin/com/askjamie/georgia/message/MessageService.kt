package com.askjamie.georgia.message

import com.askjamie.georgia.rasa.RasaClient
import com.askjamie.georgia.rasa.RasaMessage
import org.springframework.stereotype.Service

@Service
class MessageService(
        private val rasaClient: RasaClient
) {
    fun message(sender: String, message: String) = rasaClient.sendMessage(RasaMessage(sender, message))
}
