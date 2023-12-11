/*
package com.cinema.sqs

import com.cinema.model.Ticket
import com.cinema.repository.TicketRepository
import io.micronaut.jms.annotations.JMSListener
import io.micronaut.jms.annotations.Queue
import io.micronaut.jms.sqs.configuration.SqsConfiguration.CONNECTION_FACTORY_BEAN_NAME
import io.micronaut.messaging.annotation.MessageBody
import io.micronaut.serde.ObjectMapper
import org.slf4j.LoggerFactory
import java.util.concurrent.atomic.AtomicInteger

// POR ALGUM MOTIVO, DA O SEGUINTE ERRO "javax.jms.JMSException: SQSSession does not support MessageSelector.
// This should be null."
// AO IMPLEMENTAR FORMA PADRÃO DO MICRONAUT, POR ISSO ACABEI POR MESCLAR USANDO JDK KOTLIN
@JMSListener(CONNECTION_FACTORY_BEAN_NAME)
class TicketConsumer (private val ticketRepository: TicketRepository) {
    private val messageCount = AtomicInteger(0)

    @Queue(value = "ticket_queue", concurrency = "1-1")
    fun receive(@MessageBody body: String) {
        LOGGER.info("Message has been consumed. Message body: {}", body)
        val objectMapper: ObjectMapper = ObjectMapper.getDefault()
        val ticket: Ticket = objectMapper.readValue(body, Ticket::class.java)
        ticketRepository.save(ticket)
        messageCount.incrementAndGet()
    }

    fun getMessageCount(): Int {
        return messageCount.toInt()
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }
}*/
