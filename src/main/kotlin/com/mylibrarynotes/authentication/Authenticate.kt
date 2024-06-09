package com.mylibrarynotes.authentication

import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

private val hashKey = System.getenv("HASH_SECRET_KEY")
private val hMacKey = SecretKeySpec(hashKey.toByteArray(), "HmacSHA1")

fun hash(password: String): String {
    val hmac = Mac.getInstance("HmacSHA1")
    hmac.init(hMacKey)
    return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
}