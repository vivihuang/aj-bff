package com.askjamie.georgia.rasa

data class RasaOutput(
    val recipient_id: String,
    val text: String,
    val image: String,
    val buttons: List<RasaOutputButton>
)

data class RasaOutputButton(
    val title: String,
    val payload: String
)
