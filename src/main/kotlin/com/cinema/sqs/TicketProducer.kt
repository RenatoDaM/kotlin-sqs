package com.cinema.sqs

import com.cinema.request.TicketRequest
import io.micronaut.jms.annotations.JMSProducer
import io.micronaut.jms.annotations.Queue
import io.micronaut.jms.sqs.configuration.SqsConfiguration.CONNECTION_FACTORY_BEAN_NAME
import io.micronaut.messaging.annotation.MessageBody

@JMSProducer(CONNECTION_FACTORY_BEAN_NAME)
interface TicketProducer {
    @Queue("ticket_queue")
    fun send(@MessageBody body: TicketRequest)
}