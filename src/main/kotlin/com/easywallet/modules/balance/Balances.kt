package com.easywallet.modules.balance

import com.easywallet.models.BalanceInfo
import com.easywallet.models.BaseResponse
import com.easywallet.models.LogicCode

suspend fun balance(chain: String, address: String): BaseResponse<BalanceInfo> {
    return BaseResponse(
        code = LogicCode.ERROR.code,
        error = "do not support chain"
    )
}