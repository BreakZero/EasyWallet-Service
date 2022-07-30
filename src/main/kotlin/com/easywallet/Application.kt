package com.easywallet

import com.easywallet.configs.KtorClient
import com.easywallet.plugins.configureMonitoring
import com.easywallet.plugins.configureRouting
import com.easywallet.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureRouting(KtorClient.client())
    configureSerialization()
    configureMonitoring()
}
