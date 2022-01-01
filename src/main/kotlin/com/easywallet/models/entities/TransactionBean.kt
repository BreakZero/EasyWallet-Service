package com.easywallet.models.entities

import kotlinx.serialization.Serializable

@Serializable
data class TransactionBean(
    val hash: String,
    val to: String,
    val from: String,
    val amount: String,
    val inputData: String? = null
)
