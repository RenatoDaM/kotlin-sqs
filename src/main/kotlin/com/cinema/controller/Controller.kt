package com.cinema.controller

import com.cinema.model.Movie
import com.cinema.model.Ticket
import com.cinema.sqs.service.JMSService
import io.micronaut.http.HttpStatus
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status
import io.micronaut.serde.ObjectMapper

@Controller
class Controller (private val jmsService: JMSService) {
    @Post("/demo")
    @Status(HttpStatus.NO_CONTENT)
    fun publishDemoMessages(@Body movie: Movie) {
        jmsService.publish(movie.name)
    }

    @Get("/teste")
    @Status(HttpStatus.NO_CONTENT)
    suspend fun list() {
    }

    @Get("/receive")
    @Status(HttpStatus.OK)
    suspend fun receiveMessage() {
    }
}