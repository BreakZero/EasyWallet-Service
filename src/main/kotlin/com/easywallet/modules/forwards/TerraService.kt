package com.easywallet.modules.forwards

import com.easywallet.models.BalanceInfo
import com.easywallet.models.BaseResponse
import com.easywallet.models.entities.TransactionBean

object TerraService: IDataService {
    override suspend fun balance(chain: String, address: String): BaseResponse<BalanceInfo> {
        TODO("Not yet implemented")
    }

    override suspend fun transactions(
        chain: String,
        address: String,
        page: Int,
        offset: Int
    ): BaseResponse<List<TransactionBean>> {
        TODO("Not yet implemented")
    }

    override suspend fun postTx(rawData: String): BaseResponse<String> {
        TODO("Not yet implemented")
    }

    override suspend fun transaction(chain: String, address: String, hash: String): BaseResponse<String> {
        TODO("Not yet implemented")
    }
}