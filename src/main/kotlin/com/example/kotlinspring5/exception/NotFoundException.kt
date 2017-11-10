package com.example.kotlinspring5.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class NotFoundException(
        status: HttpStatus = HttpStatus.NOT_FOUND,
        reason: String? = null,
        throwable: Throwable? = null) : ResponseStatusException(status, reason, throwable)



