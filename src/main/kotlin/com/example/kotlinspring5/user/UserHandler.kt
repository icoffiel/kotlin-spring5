package com.example.kotlinspring5.user

import com.example.kotlinspring5.exception.NotFoundException
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono

@Component
class UserHandler {

    val users = listOf(User("Alex"), User("Barbara"), User("Charlie"),
            User("Daisy"), User("Evan"), User("Faye"))

    fun getAllUsers(): Mono<ServerResponse> =
            ServerResponse.ok().body(users.toFlux(), User::class.java)

    fun getUser(username: String): Mono<ServerResponse> {
        val foundUsername = users.find { it.name == username } ?: throw NotFoundException(reason = "Could not find user: $username")

        return ServerResponse.ok().body(foundUsername.toMono(), User::class.java)
    }
}