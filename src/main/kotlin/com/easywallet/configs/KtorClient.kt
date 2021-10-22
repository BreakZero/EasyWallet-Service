package com.easywallet.configs

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json

object KtorClient {
    private val client = HttpClient(CIO) {
        engine {
            requestTimeout = 100_000
            threadsCount = 8
            pipelining = true
        }

        install(JsonFeature) {
            val json: Json = kotlinx.serialization.json.Json {
                isLenient = false
                ignoreUnknownKeys = true
                allowSpecialFloatingPointValues = true
                useArrayPolymorphism = true
            }
            serializer = KotlinxSerializer(json)
        }
    }

    fun client() = client

    suspend fun getToString(url: String): String {
        val httpResponse: HttpResponse = client.get(url)
        return httpResponse.receive<String>()
    }
}