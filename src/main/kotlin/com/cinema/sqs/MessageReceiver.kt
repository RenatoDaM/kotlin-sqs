package com.cinema.sqs

import aws.sdk.kotlin.services.sqs.SqsClient
import aws.sdk.kotlin.services.sqs.model.ListQueuesRequest
import aws.sdk.kotlin.services.sqs.model.ReceiveMessageRequest
import aws.sdk.kotlin.services.sqs.model.SendMessageRequest
import com.amazon.sqs.javamessaging.ProviderConfiguration
import com.amazon.sqs.javamessaging.SQSConnection
import com.amazon.sqs.javamessaging.SQSConnectionFactory
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import io.micronaut.http.MutableHttpResponse
import jakarta.inject.Singleton


@Singleton
class MessageReceiver {
    suspend fun receiveMessages(queueUrlVal: String?): MutableHttpResponse<String>? {
        println("Retrieving messages from $queueUrlVal")
        val receiveMessageRequest = ReceiveMessageRequest {
            queueUrl = queueUrlVal
            maxNumberOfMessages = 5
        }

        SqsClient { region = "us-east-1" }.use { sqsClient ->
            val response = sqsClient.receiveMessage(receiveMessageRequest)
            response.messages?.forEach { message ->
                println(message.body)
            }
        }

        return io.micronaut.http.HttpResponse.ok<Any>().body("a")
    }

    suspend fun listQueues() {
        println("\nList Queues")

        val prefix = "https://sqs.us-east-1.amazonaws.com/123080688348/ticket_queue"
        val listQueuesRequest = ListQueuesRequest {
            queueNamePrefix = prefix
        }

        SqsClient { region = "us-east-1" }.use { sqsClient ->
            println("cheguei")
            val response = sqsClient.listQueues()
            println(response)
            response.queueUrls?.forEach { url ->
                println(url)
            }
        }
    }

    suspend fun sendMessages(queueUrlVal: String, message: String) {
        println("Sending multiple messages")
        println("\nSend message")
        val sendRequest = SendMessageRequest {
            queueUrl = queueUrlVal
            messageBody = message
            delaySeconds = 10
        }

        SqsClient { region = "us-east-1" }.use { sqsClient ->
            sqsClient.sendMessage(sendRequest)
            println("A single message was successfully sent.")
        }
    }
}