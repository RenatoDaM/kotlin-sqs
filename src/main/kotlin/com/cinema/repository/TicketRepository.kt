package com.cinema.repository

import com.cinema.model.Ticket
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.PageableRepository

@Repository
interface TicketRepository: PageableRepository<Ticket, Long> {
}