package com.easywallet.modules.forwards

import com.easywallet.models.BalanceInfo
import com.easywallet.models.BaseResponse
import java.math.BigInteger

object RPCService: IDataService {
    override suspend fun balance(chain: String, address: String): BaseResponse<BalanceInfo> {
        TODO("Not yet implemented")
    }

    override suspend fun transactions(chain: String, address: String): BaseResponse<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun transaction(chain: String, address: String, hash: String): BaseResponse<String> {
        TODO("Not yet implemented")
    }
}