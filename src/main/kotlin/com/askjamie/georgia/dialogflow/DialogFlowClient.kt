package com.askjamie.georgia.dialogflow

import com.askjamie.georgia.message.RequestMessage
import com.askjamie.georgia.message.ResponseMessage
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(value = "dialog-flow", url = "\${dialog-flow.url}")
interface DialogFlowClient {
    @PostMapping("/api/message")
    fun sendMessage(message: RequestMessage): List<ResponseMessage>
}

