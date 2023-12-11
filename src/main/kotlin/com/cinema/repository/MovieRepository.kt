package com.cinema.repository

import com.cinema.model.Movie
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.PageableRepository

@Repository
interface MovieRepository: PageableRepository<Movie, Long> {
}