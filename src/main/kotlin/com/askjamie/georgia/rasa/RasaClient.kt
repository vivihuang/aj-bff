package com.askjamie.georgia.rasa

import com.askjamie.georgia.message.RequestMessage
import com.askjamie.georgia.message.ResponseMessage
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(value = "rasa", url = "\${rasa.url}")
interface RasaClient {
    @PostMapping("/webhooks/rest/webhook")
    fun sendMessage(message: RequestMessage): List<ResponseMessage>
}
