package com.easywallet.modules.forwards

import com.easywallet.models.BalanceInfo
import com.easywallet.models.BaseResponse
import com.easywallet.models.entities.TransactionBean

interface IDataService {
    suspend fun balance(chain: String, address: String): BaseResponse<BalanceInfo>
    suspend fun transactions(chain: String, address: String, page: Int, offset: Int): BaseResponse<List<TransactionBean>>
    suspend fun transaction(chain: String, address: String, hash: String): BaseResponse<String>

    suspend fun postTx(rawData: String): BaseResponse<String>
}
