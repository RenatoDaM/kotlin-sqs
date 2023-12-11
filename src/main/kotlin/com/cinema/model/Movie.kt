package com.cinema.model

import com.cinema.annotation.NoArgsConstructor
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
@NoArgsConstructor
@Serdeable
data class Movie (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?,
    val name: String,
    val description: String,
    @OneToMany(mappedBy = "movie")
    @Column(nullable = true)
    val tickets: List<Ticket>?
)