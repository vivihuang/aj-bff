package com.askjamie.georgia.message

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseMessage(
        @JsonProperty("recipient_id")
        val recipientId: String,
        val text: String? = null,
        val image: String? = null,
        val buttons: List<Button> = emptyList()
)

data class Button(
        val title: String,
        val payload: String
)
