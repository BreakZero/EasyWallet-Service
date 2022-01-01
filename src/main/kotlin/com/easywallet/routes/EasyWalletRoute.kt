package com.easywallet.routes

import com.easywallet.modules.balance.balance
import com.easywallet.modules.coins.coins
import com.easywallet.modules.forwards.testForward
import com.easywallet.modules.transactions.transactions
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.runBlocking

fun Route.easyWallet() {
    get("/currencies") {
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
    get("/balance/{chain}/{address}") {
        val chain = call.parameters["chain"]
        val address = call.parameters["address"]
        runBlocking {
            call.respond(
                status = HttpStatusCode.OK,
                message = balance(chain.orEmpty(), address.orEmpty())
            )
        }
    }

    get("/txs/{chain}/{address}") {
        val chain = call.parameters["chain"]
        val address = call.parameters["address"]
        val page = call.parameters["page"]?.toIntOrNull() ?: 0
        val offset = call.parameters["offset"]?.toIntOrNull() ?: 50
        println("page: $page, offset: $offset")
        runBlocking {
            call.respond(
                status = HttpStatusCode.OK,
                message = transactions(chain.orEmpty(), address.orEmpty(), page, offset)
            )
        }
    }
}
