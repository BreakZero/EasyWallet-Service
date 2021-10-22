package com.easywallet.modules.balance

import com.easywallet.models.BalanceInfo
import com.easywallet.models.BaseResponse
import com.easywallet.models.LogicCode
import com.easywallet.modules.forwards.BlockChairService

internal val BLOCKCHAIR = listOf("bitcoin", "dogecoin", "ethereum")

suspend fun balance(chain: String, address: String): BaseResponse<BalanceInfo> {
    return when(chain) {
        in BLOCKCHAIR -> BlockChairService.balance(chain, address)
        else -> BaseResponse(
            code = LogicCode.ERROR.code,
            error = "do not support chain"
        )
    }
}