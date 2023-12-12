package com.cinema.request

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
data class MovieRequest (
    val name: String,
    val description: String,
)