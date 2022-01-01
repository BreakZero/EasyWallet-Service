package com.easywallet.models

import com.google.gson.annotations.SerializedName

internal data class BaseRPCResponse<T>(
    @SerializedName("jsonrpc")
    val jsonrpc: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("result")
    val result: T?
)
