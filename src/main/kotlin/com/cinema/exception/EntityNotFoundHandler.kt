package com.cinema.exception

import com.cinema.response.ErrorResponse
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import io.micronaut.json.JsonMapper
import jakarta.inject.Singleton
import jakarta.persistence.EntityNotFoundException

@Produces
@Singleton
@Requires(classes = [EntityNotFoundException::class, ExceptionHandler::class])
class EntityNotFoundHandler(private val jsonMapper: JsonMapper) :
    ExceptionHandler<EntityNotFoundException, HttpResponse<*>> {
    override fun handle(request: HttpRequest<Any>, exception: EntityNotFoundException): HttpResponse<ErrorResponse> {
        val errorResponse = ErrorResponse(code = 400, message = exception.localizedMessage)
        return HttpResponse.badRequest<ErrorResponse>().body(errorResponse)
    }
}