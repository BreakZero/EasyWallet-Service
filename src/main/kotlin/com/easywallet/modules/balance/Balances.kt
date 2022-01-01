package com.easywallet.modules.balance

import com.easywallet.configs.CoinKeys
import com.easywallet.models.BalanceInfo
import com.easywallet.models.BaseResponse
import com.easywallet.models.LogicCode
import com.easywallet.modules.forwards.EthereumService
import com.easywallet.modules.forwards.TerraService

suspend fun balance(chain: String, address: String): BaseResponse<BalanceInfo> {
    return when(chain) {
        CoinKeys.ETH_SERIES -> {
            EthereumService.balance(chain, address)
        }
        CoinKeys.TERRA -> {
            TerraService.balance(chain, address)
        }
        else -> BaseResponse(
            code = LogicCode.ERROR.code,
            error = "do not support chain"
        )
    }
}