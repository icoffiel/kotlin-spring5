package com.example.kotlinspring5

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KotlinSpring5Application

fun main(args: Array<String>) {
    SpringApplication.run(KotlinSpring5Application::class.java, *args)
}
