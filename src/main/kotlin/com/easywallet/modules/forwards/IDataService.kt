package com.easywallet.modules.forwards

import com.easywallet.models.BalanceInfo
import com.easywallet.models.BaseResponse
import java.math.BigInteger

interface IDataService {
    suspend fun balance(chain: String, address: String): BaseResponse<BalanceInfo>
    suspend fun transactions(chain: String, address: String): BaseResponse<List<String>>
    suspend fun transaction(chain: String, address: String, hash: String): BaseResponse<String>
}
