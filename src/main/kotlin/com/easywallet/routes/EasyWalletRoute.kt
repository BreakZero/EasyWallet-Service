package com.easywallet.routes

import com.easywallet.configs.ConfigKeys.INFURA_APIKEY
import com.easywallet.ext.clearHexPrefix
import com.easywallet.models.rpc.BaseRpcRequest
import com.easywallet.models.rpc.CallParameter
import com.easywallet.models.rpc.StringParameter
import com.easywallet.modules.coins.coins
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.easyWallet(client: HttpClient) {
    get("/currencies") {
        val isActive = call.request.queryParameters["is_active"] == "true"
        call.respond(
            status = HttpStatusCode.OK,
            message = coins(isActive = if (isActive) 1 else 0)
        )
    }
    route("/ethereum") {
        get("/balance/{address}") {
            val address = call.parameters["address"]
            if (address.isNullOrEmpty()) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = "address is null"
                )
            } else {
                val contract = call.request.queryParameters["contract"]
                val reqBody = contract?.let {
                    BaseRpcRequest(
                        id = 1,
                        jsonrpc = "2.0",
                        method = "eth_call",
                        params = listOf(
                            CallParameter(
                                from = address,
                                to = contract,
                                data = "0x70a08231000000000000000000000000${address.clearHexPrefix()}"
                            ),
                            StringParameter("latest")
                        )
                    )
                } ?: BaseRpcRequest(
                    id = 1,
                    jsonrpc = "2.0",
                    method = "eth_getBalance",
                    params = listOf(
                        StringParameter(address),
                        StringParameter("latest")
                    )
                )
                val response = client.post {
                    url("https://mainnet.infura.io/v3/$INFURA_APIKEY")
                    setBody(reqBody)
                }.bodyAsChannel()
                call.respond(
                    status = HttpStatusCode.OK,
                    message = response
                )
            }
        }
        get("/transaction/{hash}") { }
        get("/transactions}") { }
    }
}
