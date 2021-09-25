package com.easywallet.modules.coins

import com.easywallet.models.BaseResponse
import com.easywallet.models.LogicCode
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable

fun coins(isActive: Int): BaseResponse<List<Coin>> {
    return runBlocking {
        delay(100)
        BaseResponse(
            code = LogicCode.OK.code,
            data = coins.filter { it.isActive == isActive }
        )
    }
}

@Serializable
data class Coin(
    val coinSlug: String,
    val coinSymbol: String,
    val coinName: String,
    val decimal: Int,
    val displayDecimal: Int,
    val accentColor: String,
    val tokenType: String,
    val icon: String,
    val isActive: Int
)