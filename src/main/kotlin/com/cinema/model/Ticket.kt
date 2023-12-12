package com.cinema.model

import com.cinema.annotation.NoArgsConstructor
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@NoArgsConstructor
data class Ticket (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?,
    val price: BigDecimal,
    val date: LocalDateTime,
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = true)
    val movie: Movie
)