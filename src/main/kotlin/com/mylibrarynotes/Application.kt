package com.mylibrarynotes

import com.mylibrarynotes.plugins.*
import com.mylibrarynotes.repository.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun main() {
    embeddedServer(Netty, port = 5432, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()

    install(Authentication){

    }



    configureSecurity()
    configureSerialization()
    configureRouting()

}