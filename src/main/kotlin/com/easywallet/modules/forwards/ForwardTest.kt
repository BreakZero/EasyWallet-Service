package com.easywallet.modules.forwards

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

val client = HttpClient(CIO) {
    engine {
        requestTimeout = 100_000
    }
}

suspend fun testForward(): String {
    val httpResponse: HttpResponse =
        client.get("https://api.blockchair.com/dogecoin/dashboards/address/D9paQyatf6hg5C9PtwPiYc9vN1sLpzXmKY")
    val res = httpResponse.receive<String>()
    println(res)
    return res
}