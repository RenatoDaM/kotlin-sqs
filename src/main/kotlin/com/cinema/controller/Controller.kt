package com.cinema.controller

import com.cinema.model.Movie
import com.cinema.model.Ticket
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
class Controller (private val ticketProducer: TicketProducer) {
    @Post("/demo")
    @Status(HttpStatus.NO_CONTENT)
    fun publishDemoMessages(@Body ticket: Ticket) {
        val objectMapper: ObjectMapper = ObjectMapper.getDefault()
        ticketProducer.send(objectMapper.writeValueAsString(ticket))
    }

    @Post("/movie")
    @Status(HttpStatus.NO_CONTENT)
    fun publishMovie(@Body movie: Movie) {
        val objectMapper: ObjectMapper = ObjectMapper.getDefault()
        ticketProducer.send(objectMapper.writeValueAsString(movie))
    }
}