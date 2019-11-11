package com.askjamie.georgia.message

import com.askjamie.georgia.rasa.RasaClient
import com.askjamie.georgia.rasa.RasaMessage
import com.askjamie.georgia.rasa.RasaOutput
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class MessageServiceTest {

    @MockK
    private lateinit var rasaClient: RasaClient

    @InjectMockKs
    private lateinit var messageService: MessageService

    @Test
    fun `should call rasa client when message given sender and message`() {
        val rasaMessage = RasaMessage("sender", "message")
        val rasaOutputs = listOf(
                RasaOutput(
                        recipientId = rasaMessage.sender
                )
        )
        every { rasaClient.sendMessage(rasaMessage) } returns rasaOutputs

        val result = messageService.message(rasaMessage.sender, rasaMessage.message)

        assertThat(result).isEqualTo(rasaOutputs)
        verify(exactly = 1) { rasaClient.sendMessage(rasaMessage) }
    }
}
