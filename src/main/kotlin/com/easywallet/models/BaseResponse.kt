package com.easywallet.models

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val code: Int,
    val data: T? = null,
    val error: String? = null
)

enum class LogicCode(val code: Int) {
    OK(0), ERROR(-1)
}
