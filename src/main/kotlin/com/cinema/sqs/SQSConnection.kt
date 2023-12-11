package com.cinema.sqs

import com.amazon.sqs.javamessaging.ProviderConfiguration
import com.amazon.sqs.javamessaging.SQSConnection
import com.amazon.sqs.javamessaging.SQSConnectionFactory
import software.amazon.awssdk.services.sqs.SqsClient

class SQSConnection {
    val connectionFactory = SQSConnectionFactory(
        ProviderConfiguration(),
        SqsClient.create()
    )
    val connection: SQSConnection = connectionFactory.createConnection()
}