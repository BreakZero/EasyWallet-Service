package com.easywallet.configs

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

object KtorClient {
    private val client = HttpClient(CIO) {
        engine {
            requestTimeout = 100_000
        }
    }

    fun client() = client

    suspend fun getToString(url: String): String {
        val httpResponse: HttpResponse = client.get(url)
        return httpResponse.receive<String>()
    }
}