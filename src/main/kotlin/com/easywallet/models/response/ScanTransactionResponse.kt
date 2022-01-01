package com.easywallet.models.response

import com.easywallet.models.entities.TransactionBean
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ScanTransactionResponse<T>(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: T
)

/**
 * {
"blockNumber":"0",
"timeStamp":"1438269973",
"hash":"GENESIS_ddbd2b932c763ba5b1b7ae3b362eac3e8d40121a",
"nonce":"",
"blockHash":"",
"transactionIndex":"0",
"from":"GENESIS",
"to":"0xddbd2b932c763ba5b1b7ae3b362eac3e8d40121a",
"value":"10000000000000000000000",
"gas":"0",
"gasPrice":"0",
"isError":"0",
"txreceipt_status":"",
"input":"",
"contractAddress":"",
"cumulativeGasUsed":"0",
"gasUsed":"0",
"confirmations":"12698061"
}
 */
@Serializable
data class ScanTransactionInfo(
    val blockNumber: String,
    val timeStamp: String,
    val hash: String,
    val nonce: String,
    val blockHash: String,
    val from: String,
    val to: String,
    val value: String,
    val gas: String,
    val gasPrice: String,
    val isError: String,
    val txreceipt_status: String,
    val input: String,
    val contractAddress: String,
    val cumulativeGasUsed: String,
    val gasUsed: String,
    val confirmations: String
)

inline fun ScanTransactionInfo.mapToTransactionBean(): TransactionBean {
    return TransactionBean(
        hash = this.hash,
        to = this.to,
        from = this.from,
        amount = this.value,
        inputData = this.input
    )
}