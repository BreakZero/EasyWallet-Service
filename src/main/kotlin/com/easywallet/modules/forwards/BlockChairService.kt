package com.easywallet.modules.forwards

import com.easywallet.configs.BLOCKCHAIR_URL
import com.easywallet.configs.KtorClient
import com.easywallet.models.BalanceInfo
import com.easywallet.models.BaseResponse
import com.easywallet.models.LogicCode
import com.easywallet.models.blockchair.BalanceRemote
import com.easywallet.models.blockchair.Info
import io.ktor.client.request.*

object BlockChairService : IDataService {
    override suspend fun balance(chain: String, address: String): BaseResponse<BalanceInfo> {
        val url = "${BLOCKCHAIR_URL}${chain}/dashboards/address/$address"
        var message: String? = null
        val balance = runCatching {
            KtorClient.client()
                .get<BalanceRemote<Map<String, Info>>>(url).data.values.first().information.balance
        }.onFailure {
            message = it.message
        }.getOrNull()
        println("chain: $chain, balance: $balance")
        return balance?.let {
            BaseResponse(
                code = LogicCode.OK.code,
                data = BalanceInfo(
                    balance = it,
                    symbol = chain
                )
            )
        } ?: BaseResponse(
            code = LogicCode.ERROR.code,
            error = message ?: "get chain balance failed"
        )
    }

    override suspend fun transactions(chain: String, address: String): BaseResponse<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun transaction(chain: String, address: String, hash: String): BaseResponse<String> {
        TODO("Not yet implemented")
    }

}