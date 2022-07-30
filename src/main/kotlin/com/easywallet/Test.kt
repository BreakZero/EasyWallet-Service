package com.easywallet

import arrow.core.*
import arrow.fx.coroutines.*
import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import aws.sdk.kotlin.services.dynamodb.model.CreateGlobalTableResponse
import aws.sdk.kotlin.services.dynamodb.model.TransactGetItemsRequest
import aws.smithy.kotlin.runtime.auth.awscredentials.Credentials
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import com.easywallet.configs.ACCESS_KEY_ID
import com.easywallet.configs.SECRET_ACCESS_KEY
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

fun parse(s: String): Either<NumberFormatException, Int> =
    if (s.matches(Regex("-?[0-9]+"))) Either.Right(s.toInt())
    else Either.Left(NumberFormatException("$s is not a valid integer."))

fun reciprocal(i: Int): Either<IllegalArgumentException, Double> =
    if (i == 0) Either.Left(IllegalArgumentException("Cannot take reciprocal of 0."))
    else Either.Right(1.0 / i)

fun stringify(d: Double): String = d.toString()

fun magic(s: String): Either<Exception, String> =
    parse(s).flatMap { reciprocal(it) }.map { stringify(it) }

//sampleStart
val magic0 = magic("0")
val magic1 = magic("1")
val magicNotANumber = magic("Not a number")
//sampleEnd

val value =
//sampleStart
    Either.conditionally(true, { "Error" }, { 42 })
val someValue: Option<String> = Some("I am wrapped in something")
val emptyValue: Option<String> = none()

val resourceA = resource {
    println("${System.currentTimeMillis()}")
    delay(1000)
    println("${System.currentTimeMillis()}")
    "A"
} release { a ->
    println("Releasing $a")
}

val resourceB = resource {
    "B"
} releaseCase { b, exitCase ->
    println("Releasing $b with exit: $exitCase")
}

suspend fun main() {
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
    resp.items?.forEach {
        it.forEach { (key, value) ->
           /* if (value is AttributeValue.S) {
                println("key: $key, value: ${value.value}")
            }*/
            println("key: $key, value: ${value}")
        }
    }


    client.close()
}