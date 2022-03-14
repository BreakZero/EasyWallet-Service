package com.easywallet.modules.coins

import aws.sdk.kotlin.runtime.auth.credentials.Credentials
import aws.sdk.kotlin.runtime.auth.credentials.CredentialsProvider
import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import aws.sdk.kotlin.services.dynamodb.paginators.items
import aws.sdk.kotlin.services.dynamodb.paginators.scanPaginated
import com.easywallet.configs.ACCESS_KEY_ID
import com.easywallet.configs.SECRET_ACCESS_KEY
import com.easywallet.models.BaseResponse
import com.easywallet.models.LogicCode
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable

fun coins(isActive: Int): BaseResponse<List<Coin>> {
    return runBlocking {

        val client = DynamoDbClient {
            region = "us-east-2"
            credentialsProvider = object : CredentialsProvider {
                override suspend fun getCredentials(): Credentials {
                    return Credentials(
                        secretAccessKey = SECRET_ACCESS_KEY,
                        accessKeyId = ACCESS_KEY_ID
                    )
                }
            }
        }
        val resp = client.scan {
            tableName = "CoinConfig"
        }
        val result = resp.items?.map {
            Coin(
                coinSlug = (it["slug"] as AttributeValue.S).value,
                coinSymbol = (it["symbol"] as AttributeValue.S).value,
                decimal = (it["decimal"] as AttributeValue.N).value.toInt(),
                iconUrl = (it["icon"] as AttributeValue.S).value,
                contractAddress = (it["contract_address"] as? AttributeValue.S)?.value,
                tag = (it["tag"] as? AttributeValue.S)?.value
            )
        }
        BaseResponse(
            code = LogicCode.OK.code,
            data = result ?: emptyList()
        )
    }
}

@Serializable
data class Coin(
    val coinSlug: String,
    val coinSymbol: String,
    val decimal: Int,
    val iconUrl: String,
    val contractAddress: String? = null,
    val tag: String? = null
)