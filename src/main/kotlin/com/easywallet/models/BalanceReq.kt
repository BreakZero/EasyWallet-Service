package com.easywallet.models

import kotlinx.serialization.Serializable

@Serializable
internal data class BalanceReq(
    val data: String,
    val from: String,
    val to: String
)