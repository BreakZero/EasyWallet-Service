package com.easywallet.plugins

import com.easywallet.routes.easyWallet
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.routing.*

fun Application.configureRouting() {


    routing {
        easyWallet()
        static {
            resources("static")
        }
    }
}

