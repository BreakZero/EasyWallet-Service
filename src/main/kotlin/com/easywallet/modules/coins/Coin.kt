package com.easywallet.modules.coins

import com.easywallet.models.BaseResponse
import com.easywallet.models.LogicCode
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File
import java.nio.file.Files

fun coins(isActive: Int): BaseResponse<List<Coin>> {
    return BaseResponse(
        code = LogicCode.OK.code,
        data = coins.filter { it.isActive == isActive }
    )
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