package com.easywallet.routes

import com.easywallet.modules.balance.balance
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
    get("/{chain}/{address}") {
        val chain = call.parameters["chain"]
        val address = call.parameters["address"]
        runBlocking {
            call.respond(
                status = HttpStatusCode.OK,
                message = balance(chain.orEmpty(), address.orEmpty())
            )
        }
    }
}
