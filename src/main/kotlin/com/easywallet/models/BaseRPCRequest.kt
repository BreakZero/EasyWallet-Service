package com.easywallet.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

/**
 * {
    "jsonrpc": "2.0",
    "method": "eth_getBalance",
    "params": [
    "0x81080a7e991bcdddba8c2302a70f45d6bd369ab5",
    "latest"
    ],
    "id": 1
    }
 */

@Serializable
data class BaseRPCRequest(
    val jsonrpc: String,
    val method: String,
    @Contextual val params: List<@Polymorphic Any>,
    val id: Int
)