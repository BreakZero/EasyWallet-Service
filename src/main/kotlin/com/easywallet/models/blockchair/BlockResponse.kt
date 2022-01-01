package com.easywallet.models.blockchair

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class BalanceRemote<T>(
    val data: T
)

@Serializable
data class Info(
    @JsonNames("address")
    val information: BalanceInfo
)

@Serializable
data class BalanceInfo(
    val balance: Long
)

@Serializable
data class Balance(
    val balance: Long
)