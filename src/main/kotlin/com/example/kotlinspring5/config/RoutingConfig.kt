package com.example.kotlinspring5.config

import com.example.kotlinspring5.exception.NotFoundException
import com.example.kotlinspring5.user.UserHandler
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class RoutingConfig {
    private val usernamePathVariable = "username"

    @Bean
    fun routes(userHandler: UserHandler): RouterFunction<ServerResponse> = router {
        "/api".nest {
            GET("/"){
                userHandler.getAllUsers()
            }
            GET("/{$usernamePathVariable}") { req ->
                val username = req.pathVariable(usernamePathVariable)
                userHandler.getUser(username)
            }
        }

    }.filter { request, next ->
        try {
            next.handle(request)
        } catch (ex: Exception) {
            log.error("An error occured", ex)
            when (ex) {
                is NotFoundException -> ServerResponse.notFound().build()
                else -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
            }
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(RoutingConfig::class.java)
    }
}