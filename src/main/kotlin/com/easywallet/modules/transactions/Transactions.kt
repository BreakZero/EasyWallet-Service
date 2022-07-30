package com.easywallet.modules.transactions

import com.easywallet.models.BaseResponse
import com.easywallet.models.LogicCode
import com.easywallet.models.entities.TransactionBean

suspend fun transactions(chain: String, address: String, page: Int, offset: Int): BaseResponse<List<TransactionBean>> {
    return BaseResponse(
        code = LogicCode.ERROR.code,
        error = "do not support chain"
    )
}