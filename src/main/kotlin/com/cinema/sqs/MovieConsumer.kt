package com.cinema.sqs

import com.cinema.model.Movie
import com.cinema.model.Ticket
import com.cinema.repository.MovieRepository
import com.cinema.repository.TicketRepository
import io.micronaut.jms.annotations.JMSListener
import io.micronaut.jms.annotations.Queue
import io.micronaut.jms.sqs.configuration.SqsConfiguration
import io.micronaut.messaging.annotation.MessageBody
import io.micronaut.serde.ObjectMapper
import org.slf4j.LoggerFactory
import java.util.concurrent.atomic.AtomicInteger

@JMSListener(SqsConfiguration.CONNECTION_FACTORY_BEAN_NAME)
class MovieConsumer (private val movieRepository: MovieRepository) {
    private val messageCount = AtomicInteger(0)

    @Queue(value = "ticket_queue", concurrency = "1-3")
    fun receive(@MessageBody body: String) {
        LOGGER.info("Message has been consumed. Message body: {}", body)
        val objectMapper: ObjectMapper = ObjectMapper.getDefault()
        val movie: Movie = objectMapper.readValue(body, Movie::class.java)
        movieRepository.save(movie)
        messageCount.incrementAndGet()
    }

    fun getMessageCount(): Int {
        return messageCount.toInt()
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }
}