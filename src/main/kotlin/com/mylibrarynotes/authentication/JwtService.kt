package com.mylibrarynotes.authentication

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.mylibrarynotes.data.model.User

class JwtService {


    private val issuer = "noteServer"
    private val secret = System.getenv("JWT_SECRET")
    private val algorithm = Algorithm.HMAC512(secret)


    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()


    fun generateToken(user: User) : String {
        return JWT.create()
            .withSubject("NoteAuth")
            .withIssuer(issuer)
            .withClaim("email",user.email)
            .sign(algorithm)
    }
}