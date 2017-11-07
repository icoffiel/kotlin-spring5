package com.example.kotlinspring5.config

import com.example.kotlinspring5.exception.ExceptionHandler
import com.example.kotlinspring5.user.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class RoutingConfig(val exceptionHandler: ExceptionHandler) {

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
        exceptionHandler.handleExceptions(next, request)
    }
}