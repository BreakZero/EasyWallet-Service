package com.easywallet.modules.coins

import com.easywallet.models.BaseResponse
import com.easywallet.models.LogicCode
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun coins(isActive: Int): BaseResponse<List<Coin>> {
    return transaction {
        kotlin.runCatching {
            Coins.select(Coins.is_active.eq(isActive)).map {
                Coin(
                    coinSlug = it[Coins.coin_slug],
                    coinSymbol = it[Coins.coin_symbol],
                    coinName = it[Coins.coin_name],
                    decimal = it[Coins.coin_decimal],
                    displayDecimal = it[Coins.display_decimal],
                    accentColor = it[Coins.accent_color],
                    tokenType = it[Coins.token_type],
                    isActive = it[Coins.is_active]
                )
            }
        }.getOrNull()?.let {
            BaseResponse(
                code = LogicCode.OK.code,
                data = it
            )
        } ?: BaseResponse(
            code = LogicCode.ERROR.code,
            error = "get coin error"
        )
    }
}

object Coins : Table("coin") {
    val coin_slug = varchar("coin_slug", length = 32).primaryKey()
    val coin_symbol = varchar("coin_symbol", length = 32)
    val coin_name = varchar("coin_name", length = 32)
    val coin_decimal = integer("coin_decimal")
    val display_decimal = integer("display_decimal")
    val accent_color = varchar("accent_color", length = 32)
    val token_type = varchar("token_type", length = 32)
    val is_active = integer("is_active")
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
    val isActive: Int
)