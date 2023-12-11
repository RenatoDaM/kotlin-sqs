package com.cinema.annotation

import io.micronaut.context.annotation.AliasFor
import io.micronaut.context.annotation.Executable
import io.micronaut.jms.listener.JMSListenerErrorHandler
import io.micronaut.jms.listener.JMSListenerSuccessHandler
import io.micronaut.messaging.annotation.MessageMapping
import kotlin.reflect.KClass

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Executable(processOnStartup = true)
@MessageMapping
annotation class Queue(
    @get:AliasFor(annotation = MessageMapping::class, member = "value") val value: String,
    @get:Deprecated("") val concurrency: String = "1-1",
    val serializer: String = "",
    @get:Deprecated("") val executor: String = "",
    val acknowledgeMode: Int = 1,
    val transacted: Boolean = false,
    val messageSelector: String = "",
    val successHandlers: Array<KClass<out Any>> = [],
    val errorHandlers: Array<KClass<out Any>> = []
)

