package com.example.kotlinspring5.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ExceptionHandler {
    fun handleExceptions(next: HandlerFunction<ServerResponse>, request: ServerRequest?): Mono<ServerResponse>? {
        return try {
            next.handle(request)
        } catch (ex: Exception) {
            log.warn("An exception was caught", ex)
            when (ex) {
                is NotFoundException -> ServerResponse.notFound().build()
                else -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
            }
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(ExceptionHandler::class.java)
    }
}