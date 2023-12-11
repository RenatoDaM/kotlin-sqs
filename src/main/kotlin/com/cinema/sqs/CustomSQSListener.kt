package com.cinema.sqs

import javax.jms.JMSException
import javax.jms.Message
import javax.jms.MessageListener
import javax.jms.TextMessage


internal class CustomSQSListener : MessageListener {
    override fun onMessage(message: Message) {
        try {
            // Cast the received message as TextMessage and print the text to screen.
            println("Received: " + (message as TextMessage).text)
        } catch (e: JMSException) {
            e.printStackTrace()
        }
    }
}