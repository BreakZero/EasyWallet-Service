package com.easywallet.models

import kotlinx.serialization.Serializable

@Serializable
data class BalanceInfo(
    val balance: Long,
    val symbol: String
)
