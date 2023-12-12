package com.cinema.request

import com.cinema.model.Movie
import com.fasterxml.jackson.annotation.JsonFormat
import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDateTime

@Serdeable
@Introspected
data class TicketRequest(
    @NotNull
    val price: BigDecimal,
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val date: LocalDateTime,
    val movieId: Long
)
