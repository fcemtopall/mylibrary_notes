package com.mylibrarynotes

import com.mylibrarynotes.authentication.JwtService
import com.mylibrarynotes.authentication.hash
import com.mylibrarynotes.data.model.User
import com.mylibrarynotes.plugins.*
import com.mylibrarynotes.repository.DatabaseFactory
import com.mylibrarynotes.repository.Repository
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()
    val db = Repository()
    val jwtService = JwtService()
    val hashFunction = { s: String -> hash(s) }

    configureSecurity()
    configureSerialization()
    configureRouting()

    routing {
        get ("/token"){
            val email = call.request.queryParameters["email"]!!
            val password = call.request.queryParameters["password"]!!
            val username = call.request.queryParameters["username"]!!

            val user = User(email, hashFunction(password), username)
            call.respond(jwtService.generateToken(user))
        }
    }

}