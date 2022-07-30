package com.easywallet.plugins

import com.easywallet.routes.easyWallet
import io.ktor.client.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting(ktorClient: HttpClient) {
    routing {
        easyWallet(ktorClient)
        static {
            resources("static")
        }
    }
}

