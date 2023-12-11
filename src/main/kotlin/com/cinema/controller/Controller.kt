package com.cinema.controller

import com.cinema.model.Ticket
import com.cinema.sqs.MessageReceiver
import com.cinema.sqs.TicketProducer
import io.micronaut.http.HttpStatus
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status
import io.micronaut.serde.ObjectMapper

@Controller
class Controller (private val ticketProducer: TicketProducer,
    private val messageReceiver: MessageReceiver) {
    @Post("/demo")
    @Status(HttpStatus.NO_CONTENT)
    fun publishDemoMessages(@Body ticket: Ticket) {
        val objectMapper: ObjectMapper = ObjectMapper.getDefault()
        ticketProducer.send(objectMapper.writeValueAsString(ticket))
    }

    @Get("/teste")
    @Status(HttpStatus.NO_CONTENT)
    suspend fun list() {
        messageReceiver.sendMessages("https://sqs.us-east-1.amazonaws.com/123080688348/ticket_queue", "ooooakodsksao")
    }

    @Get("/receive")
    @Status(HttpStatus.OK)
    suspend fun receiveMessage(): MutableHttpResponse<String>? {
        return messageReceiver.receiveMessages("https://sqs.us-east-1.amazonaws.com/123080688348/ticket_queue")
    }
}