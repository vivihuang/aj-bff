package com.askjamie.georgia.rasa

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(value = "rasa", url = "\${rasa.url}")
interface RasaClient {
    @PostMapping("/webhooks/rest/webhook")
    fun sendMessage(message: RasaMessage): List<RasaOutput>
}
