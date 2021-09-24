package com.easywallet.plugins

import io.ktor.application.*
import org.jetbrains.exposed.sql.Database

fun Application.initDB() {
    val url = "jdbc:mysql://localhost:3306/easy_wallet?useUnicode=true&serverTimezone=UTC&useSSL=false"
    val driver = "com.mysql.cj.jdbc.Driver"
    kotlin.runCatching {
        Database.connect(url = url, user = "root", password = "098765", driver = driver)
    }.onFailure {
        it.printStackTrace()
    }.onSuccess {
        print(it.dialect)
    }
}