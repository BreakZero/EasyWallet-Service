package com.easywallet.modules.balance

import com.easywallet.configs.BLOCKCHAIR_URL
import com.easywallet.configs.KtorClient
import com.easywallet.models.BaseResponse
import com.easywallet.models.LogicCode
import io.ktor.client.request.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

suspend fun balance(chain: String, address: String): BaseResponse<Long> {
    val url = when (chain) {
        "bitcoin" -> "${BLOCKCHAIR_URL}bitcoin/dashboards/address/$address"
        "dogecoin" -> "${BLOCKCHAIR_URL}dogecoin/dashboards/address/$address"
        else -> throw UnsupportedOperationException("unsupport url")
    }
    var message: String? = null
    val balance = runCatching {
        KtorClient.client()
            .get<BalanceRemote<Map<String, Info>>>(url).data.values.first().information.balance
    }.onFailure {
        message = it.message
    }.getOrNull()

    return balance?.let {
        BaseResponse(
            code = LogicCode.OK.code,
            data = balance
        )
    } ?: BaseResponse(
        code = LogicCode.ERROR.code,
        error = message ?: "get chain balance failed"
    )
}

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