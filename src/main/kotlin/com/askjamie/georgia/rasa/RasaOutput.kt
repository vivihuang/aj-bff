package com.askjamie.georgia.rasa

import com.fasterxml.jackson.annotation.JsonProperty

data class RasaOutput(
        @JsonProperty("recipient_id")
        val recipientId: String,
        val text: String? = null,
        val image: String? = null,
        val buttons: List<RasaOutputButton> = emptyList()
)

data class RasaOutputButton(
        val title: String,
        val payload: String
)
