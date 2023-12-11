package com.cinema.sqs.listener

import javax.jms.JMSException
import javax.jms.Message
import javax.jms.MessageListener
import javax.jms.TextMessage


class TicketListener: MessageListener {
    override fun onMessage(message: Message?) {
        try {
            println("Received: " + (message as TextMessage).text)
        } catch (e: JMSException) {
            e.printStackTrace()
        }
    }
}