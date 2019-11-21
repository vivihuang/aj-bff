package com.askjamie.georgia.message

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(controllers = [MessageController::class])
internal class MessageControllerTest(@Autowired private val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var messageService: MessageService

    @BeforeEach
    internal fun setUp() {
        every { messageService.messageToRasa(any(), any()) } returns emptyList()
    }

    @AfterEach
    internal fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `should return when message given command without sender`() {
        val command = SendMessageCommand(message = "message")

        mockMvc.perform(
                post("/api/message/rasa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonObjectMapper().writeValueAsString(command))
        )
                .andExpect(MockMvcResultMatchers.status().isOk)

        verify(exactly = 1) {
            messageService.messageToRasa(isNull(inverse = true), command.message)
        }
    }

    @Test
    fun `should return when message given command with existing sender`() {
        val command = SendMessageCommand(sender = "sender", message = "message")

        mockMvc.perform(
                post("/api/message/rasa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonObjectMapper().writeValueAsString(command))
        )
                .andExpect(MockMvcResultMatchers.status().isOk)

        verify(exactly = 1) {
            messageService.messageToRasa(command.sender!!, command.message)
        }
    }
}
