package com.easywallet.modules.forwards

import com.easywallet.configs.APIKeys
import com.easywallet.configs.KtorClient
import com.easywallet.ext.clearHexPrefix
import com.easywallet.models.*
import com.easywallet.models.entities.TransactionBean
import com.easywallet.models.response.ScanTransactionInfo
import com.easywallet.models.response.ScanTransactionResponse
import com.easywallet.models.response.mapToTransactionBean
import com.google.protobuf.Api
import io.ktor.client.request.*
import io.ktor.http.*

private const val MAINNET_RPC = "https://mainnet.infura.io/v3/"
private const val ROPSTEN_RPC = "https://ropsten.infura.io/v3/"

/**
 * BaseRPCRequest(
id = 1,
jsonrpc = "2.0",
method = "eth_call",
params = listOf(
BalanceReq(
from = address,
to = "0xa0b73e1ff0b80914ab6fe0444e65848c4c34450b",
data = "0x70a08231000000000000000000000000${address.clearHexPrefix()}"
),
"latest"
)
)
 */

object EthereumService : IDataService {
    override suspend fun balance(chain: String, address: String): BaseResponse<BalanceInfo> {
        var message: String? = null

        val balance = kotlin.runCatching {
            KtorClient.client().post<BaseRPCResponse<String>>("${MAINNET_RPC}${APIKeys.INFURA}") {
                contentType(ContentType.Application.Json)
                body = BaseRPCRequest(
                    id = 1,
                    jsonrpc = "2.0",
                    method = "eth_getBalance",
                    params = listOf(address, "latest")
                )
            }.result
        }.onFailure {
            it.printStackTrace()
            message = it.message
        }.getOrNull()
        return balance?.let {
            BaseResponse(
                code = LogicCode.OK.code,
                data = BalanceInfo(
                    balance = it.clearHexPrefix().toLongOrNull(radix = 16) ?: 0L,
                    symbol = chain
                )
            )
        } ?: BaseResponse(
            code = LogicCode.ERROR.code,
            error = message ?: "get chain balance failed"
        )
    }

    override suspend fun transactions(
        chain: String, address: String, page: Int, offset: Int
    ): BaseResponse<List<TransactionBean>> {
        val result = kotlin.runCatching {
            KtorClient.client().get<ScanTransactionResponse<List<ScanTransactionInfo>>>("https://api.etherscan.io/api") {
                parameter("module", "account")
                parameter("action", "txlist")
                parameter("address", address)
                parameter("page", page)
                parameter("offset", offset)
                parameter("apikey", APIKeys.ETH_SCAN)
            }.result
        }.onFailure {
            it.printStackTrace()
        }.getOrNull()
        return result?.let {
            BaseResponse(
                code = LogicCode.OK.code,
                data = result.map {
                    it.mapToTransactionBean()
                }
            )
        } ?: BaseResponse(
            code = LogicCode.ERROR.code,
            error = "somethings went wrong"
        )
    }

    override suspend fun postTx(rawData: String): BaseResponse<String> {
        TODO("Not yet implemented")
    }

    override suspend fun transaction(chain: String, address: String, hash: String): BaseResponse<String> {
        TODO("Not yet implemented")
    }
}