package com.example.kotlinspring5.exception

class NotFoundException : RuntimeException {
    constructor(message: String): super(message)
    constructor(message: String, cause: Throwable): super(message, cause)
    constructor(): super()
}



