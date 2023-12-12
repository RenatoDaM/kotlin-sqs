package com.cinema.service

import com.cinema.repository.MovieRepository
import com.cinema.repository.TicketRepository
import com.cinema.request.TicketRequest
import com.cinema.sqs.TicketConsumer
import com.cinema.sqs.TicketProducer
import jakarta.inject.Singleton
import jakarta.persistence.EntityNotFoundException

@Singleton
class TicketService (private val sqs: TicketProducer, private val movieRepository: MovieRepository, private val ticketRepository: TicketRepository) {
    fun send(ticket: TicketRequest) {
        movieRepository.findById(ticket.movieId).orElseThrow {
            throw EntityNotFoundException("Movie's ticket not found")
        }
        sqs.send(ticket)
    }
}