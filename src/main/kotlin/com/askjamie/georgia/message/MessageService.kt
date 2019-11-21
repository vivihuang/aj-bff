package com.askjamie.georgia.message

import com.askjamie.georgia.dialogflow.DialogFlowClient
import com.askjamie.georgia.rasa.RasaClient
import org.springframework.stereotype.Service

@Service
class MessageService(
        private val rasaClient: RasaClient,
        private val dialogFlowClient: DialogFlowClient
) {
    fun messageToRasa(sender: String, message: String) = rasaClient.sendMessage(RequestMessage(sender, message))

    fun messageToDialogFlow(sender: String, message: String)
            = dialogFlowClient.sendMessage(RequestMessage(sender, message))
}
