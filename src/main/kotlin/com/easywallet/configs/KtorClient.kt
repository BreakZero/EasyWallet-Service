package com.easywallet.configs

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

object KtorClient {
    private val client = HttpClient(CIO) {
        engine {
            requestTimeout = 100_000
            threadsCount = 8
            pipelining = true
        }
        install(JsonFeature) {
            serializer = GsonSerializer() {
                setPrettyPrinting()
                disableHtmlEscaping()
            }
        }
        /*install(JsonFeature) {
            val json: Json = kotlinx.serialization.json.Json {
                isLenient = false
                ignoreUnknownKeys = true
                allowSpecialFloatingPointValues = true
                useArrayPolymorphism = true
                serializersModule = SerializersModule {
                    polymorphic(Any::class, BalanceReq::class, BalanceReq.serializer())
                    polymorphic(Any::class, String::class, String.serializer())
                    contextual(Any::class) {
                        BalanceReq.serializer()
                        String.serializer()
                    }
                }
            }
            serializer = KotlinxSerializer(json)
        }*/
    }

    fun client() = client

    suspend fun getToString(url: String): String {
        val httpResponse: HttpResponse = client.get(url)
        return httpResponse.receive<String>()
    }

    suspend fun postToString(url: String, params: String): String {
        val httpResponse: HttpResponse = client.post(url) {
            body = params
        }
        return httpResponse.receive<String>()
    }
}