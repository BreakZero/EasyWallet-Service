package com.easywallet.modules.forwards

import com.easywallet.configs.KtorClient

suspend fun testForward(): String {
    return KtorClient.getToString("https://api.blockchair.com/dogecoin/dashboards/address/D9paQyatf6hg5C9PtwPiYc9vN1sLpzXmKY")
}