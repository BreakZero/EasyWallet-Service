package com.easywallet.modules.transactions

import com.easywallet.configs.CoinKeys
import com.easywallet.models.BaseResponse
import com.easywallet.models.LogicCode
import com.easywallet.models.entities.TransactionBean
import com.easywallet.modules.forwards.EthereumService
import com.easywallet.modules.forwards.TerraService

suspend fun transactions(chain: String, address: String, page: Int, offset: Int): BaseResponse<List<TransactionBean>> {
    return when (chain) {
        CoinKeys.ETH_SERIES -> {
            EthereumService.transactions(chain, address, page, offset)
        }
        CoinKeys.TERRA -> {
            TerraService.transactions(chain, address, page, offset)
        }
        else -> BaseResponse(
            code = LogicCode.ERROR.code,
            error = "do not support chain"
        )
    }
}