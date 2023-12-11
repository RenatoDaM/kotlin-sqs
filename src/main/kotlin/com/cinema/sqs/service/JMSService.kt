package com.cinema.sqs.service

import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper
import com.amazon.sqs.javamessaging.ProviderConfiguration
import com.amazon.sqs.javamessaging.SQSConnection
import com.amazon.sqs.javamessaging.SQSConnectionFactory
import com.cinema.sqs.listener.TicketListener
import jakarta.inject.Singleton
import software.amazon.awssdk.services.sqs.SqsClient
import javax.jms.MessageConsumer
import javax.jms.MessageProducer
import javax.jms.Queue
import javax.jms.Session

@Singleton
class JMSService {
    // Create a new connection factory with all defaults (credentials and region) set automatically
    private var connectionFactory: SQSConnectionFactory = SQSConnectionFactory(
        ProviderConfiguration(),
        SqsClient.create()
    )
    var connection: SQSConnection = connectionFactory.createConnection()
    var client: AmazonSQSMessagingClientWrapper = connection.wrappedAmazonSQSClient

    init {
        if (!client.queueExists("ticket_queue")) {
            client.createQueue("ticket_queue");
        }
        val session: Session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
        val consumer: MessageConsumer = session.createConsumer(session.createQueue("ticket_queue"))
        consumer.messageListener = TicketListener()
        connection.start();
        Thread.sleep(1000);
    }

    fun publish(textMessage: String) {
        val session: Session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
        val queue: Queue = session.createQueue("ticket_queue")
        val producer: MessageProducer = session.createProducer(queue)
        val message = session.createTextMessage(textMessage)
        producer.send(message)
        println("Published!!!")
    }
}