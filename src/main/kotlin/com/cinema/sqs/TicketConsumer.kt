package com.cinema.sqs

import com.cinema.model.Movie
import com.cinema.model.Ticket
import com.cinema.repository.MovieRepository
import com.cinema.repository.TicketRepository
import com.cinema.request.TicketRequest
import io.micronaut.jms.annotations.JMSListener
import io.micronaut.jms.annotations.Queue
import io.micronaut.jms.sqs.configuration.SqsConfiguration.CONNECTION_FACTORY_BEAN_NAME
import io.micronaut.messaging.annotation.MessageBody
import org.slf4j.LoggerFactory
import java.util.concurrent.atomic.AtomicInteger

@JMSListener(CONNECTION_FACTORY_BEAN_NAME)
class TicketConsumer (private val ticketRepository: TicketRepository, private val movieRepository: MovieRepository) {

    private val messageCount = AtomicInteger(0)
    @Queue(value = "ticket_queue", concurrency = "1-3")
    fun receive(@MessageBody body: TicketRequest) {
        LOGGER.info("Message has been consumed. Message body: {}", body)

        val ticketToPersist = Ticket(id = null, price = body.price,
            date = body.date, movie = Movie(body.movieId))

        ticketRepository.save(ticketToPersist)
        messageCount.incrementAndGet()
    }

    fun getMessageCount(): Int {
        return messageCount.toInt()
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }
}
