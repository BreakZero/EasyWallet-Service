package com.easywallet.configs

import com.easywallet.models.rpc.CallParameter
import com.easywallet.models.rpc.IntListParameter
import com.easywallet.models.rpc.Parameter
import com.easywallet.models.rpc.StringParameter
import com.easywallet.models.serializers.IntListParameterSerializer
import com.easywallet.models.serializers.StringParameterSerializer
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

object KtorClient {
    private val client = HttpClient(CIO) {
        defaultRequest {
            header("Content-type", "application/json")
            contentType(ContentType.Application.Json)
        }
        engine {
            requestTimeout = 100_000
            threadsCount = 8
            pipelining = true
        }
        install(ContentNegotiation) {
            json(
                kotlinx.serialization.json.Json {
                    useArrayPolymorphism = true
                    prettyPrint = true
                    ignoreUnknownKeys = true
                    allowStructuredMapKeys = true
                    serializersModule = SerializersModule {
                        polymorphic(Parameter::class) {
                            subclass(CallParameter::class, CallParameter.serializer())
                            subclass(StringParameter::class, StringParameterSerializer)
                            subclass(IntListParameter::class, IntListParameterSerializer)
                        }
                    }
                }
            )
        }
    }

    fun client() = client

    suspend fun getToString(url: String): String {
        val httpResponse: HttpResponse = client.get(url)
        return httpResponse.bodyAsText()
    }

    suspend fun postToString(url: String, params: String): String {
        val httpResponse: HttpResponse = client.post(url) {
            setBody(params)
        }
        return httpResponse.bodyAsText()
    }
}