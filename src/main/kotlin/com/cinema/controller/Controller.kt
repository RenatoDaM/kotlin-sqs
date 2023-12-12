package com.cinema.controller

import com.cinema.model.Movie
import com.cinema.model.Ticket
import com.cinema.repository.MovieRepository
import com.cinema.request.TicketRequest
import com.cinema.service.TicketService
import com.cinema.sqs.TicketConsumer
import com.cinema.sqs.TicketProducer
import io.micronaut.http.HttpStatus
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status
import io.micronaut.serde.ObjectMapper
import jakarta.persistence.EntityNotFoundException

@Controller
class Controller (private val ticketService: TicketService, private val ticketProducer: TicketProducer, private val movieRepository: MovieRepository) {
    @Post("/ticket")
    @Status(HttpStatus.NO_CONTENT)
    fun publishDemoMessages(@Body ticket: TicketRequest) {
        ticketService.send(ticket)
    }
}