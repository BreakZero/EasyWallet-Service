package com.easywallet.routes

import com.easywallet.modules.coins.coins
import com.easywallet.modules.forwards.testForward
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.runBlocking

fun Route.easyWallet() {
    get("/coins") {
        val isActive = call.request.queryParameters["is_active"] == "true"
        call.respond(
            status = HttpStatusCode.OK,
            message = coins(isActive = if (isActive) 1 else 0)
        )
    }
    get("/forward") {
        runBlocking {
            call.respond(
                status = HttpStatusCode.OK,
                message = testForward()
            )
        }
    }
}
