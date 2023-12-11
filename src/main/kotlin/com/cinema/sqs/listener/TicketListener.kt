package com.cinema.sqs.listener

import com.cinema.model.Movie
import com.cinema.model.Ticket
import com.cinema.repository.TicketRepository
import io.micronaut.serde.ObjectMapper
import jakarta.inject.Singleton
import javax.jms.JMSException
import javax.jms.Message
import javax.jms.MessageListener
import javax.jms.TextMessage

@Singleton
class TicketListener (private val ticketRepository: TicketRepository): MessageListener {
    override fun onMessage(message: Message?) {
        try {
            println("Received: " + (message as TextMessage).text)
            val objectMapper: ObjectMapper = ObjectMapper.getDefault()
            val ticket: Ticket = objectMapper.readValue(message.text, Ticket::class.java)
            ticketRepository.save(ticket)
        } catch (e: JMSException) {
            e.printStackTrace()
        }
    }
}