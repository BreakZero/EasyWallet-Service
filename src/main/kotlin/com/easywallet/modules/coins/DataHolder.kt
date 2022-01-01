package com.easywallet.modules.coins

//private val BASE_URL = "http://localhost:8080/"
private val BASE_URL = ""

internal val supportCoins = listOf(
    Coin(
        coinSlug = "ethereum",
        coinSymbol = "ETH",
        coinName = "Ethereum",
        decimal = 18,
        displayDecimal = 8,
        accentColor = "#757575",
        tokenType = "MAIN_COIN",
        iconSuffix = "icons/ethereum.png",
        isActive = 1
    )
)

internal val coins = listOf(
    Coin(
        coinSlug = "atom-main",
        coinSymbol = "ATOM",
        coinName = "Cosmos",
        decimal = 6,
        displayDecimal = 6,
        accentColor = "#263238",
        tokenType = "MAIN_COIN",
        iconSuffix = "${BASE_URL}icons/uni.png",
        isActive = 0
    ),
    Coin(
        coinSlug = "bitcoin-cash-main",
        coinSymbol = "BCH",
        coinName = "Bitcoin Cash",
        decimal = 8,
        displayDecimal = 8,
        accentColor = "#F9A825",
        tokenType = "MAIN_COIN",
        iconSuffix = "${BASE_URL}icons/bitcoin-cash-2.png",
        isActive = 0
    ),
    Coin(
        coinSlug = "bnb-smart",
        coinSymbol = "BNB",
        coinName = "Binance Smart",
        decimal = 18,
        displayDecimal = 8,
        accentColor = "#FFAB00",
        tokenType = "MAIN_COIN",
        iconSuffix = "${BASE_URL}icons/binance-coin.png",
        isActive = 0
    ),
    Coin(
        coinSlug = "bnb-smart-legacy",
        coinSymbol = "BNB",
        coinName = "Binance Smart",
        decimal = 18,
        displayDecimal = 8,
        accentColor = "#FFAB00",
        tokenType = "MAIN_COIN",
        iconSuffix = "${BASE_URL}icons/binance-coin.png",
        isActive = 0
    ),
    Coin(
        coinSlug = "comp-erc20",
        coinSymbol = "COMP",
        coinName = "Compound",
        decimal = 18,
        displayDecimal = 8,
        accentColor = "#00E676",
        tokenType = "ERC20TOKEN",
        iconSuffix = "${BASE_URL}icons/compound.png",
        isActive = 0
    ),
    Coin(
        coinSlug = "cro-erc20",
        coinSymbol = "CRO",
        coinName = "Crypto.com Coin",
        decimal = 8,
        displayDecimal = 8,
        accentColor = "#1A237E",
        tokenType = "ERC20TOKEN",
        iconSuffix = "${BASE_URL}icons/cro.png",
        isActive = 0
    ),
    Coin(
        coinSlug = "dai-erc20",
        coinSymbol = "DAI",
        coinName = "Dai Stablecoin",
        decimal = 18,
        displayDecimal = 8,
        accentColor = "#FFEB3B",
        tokenType = "ERC20TOKEN",
        iconSuffix = "${BASE_URL}icons/dai.png",
        isActive = 0
    ),
    Coin(
        coinSlug = "doge-main",
        coinSymbol = "DOGE",
        coinName = "Dogecoin",
        decimal = 8,
        displayDecimal = 8,
        accentColor = "#FFD54F",
        tokenType = "MAIN_COIN",
        iconSuffix = "${BASE_URL}icons/dogecoin.png",
        isActive = 0
    ),
    Coin(
        coinSlug = "eth-main",
        coinSymbol = "ETH",
        coinName = "Ethereum",
        decimal = 18,
        displayDecimal = 8,
        accentColor = "#757575",
        tokenType = "MAIN_COIN",
        iconSuffix = "${BASE_URL}icons/ethereum.png",
        isActive = 0
    ),
    Coin(
        coinSlug = "matic-main",
        coinSymbol = "MATIC",
        coinName = "Polygon",
        decimal = 9,
        displayDecimal = 8,
        accentColor = "#00FFD4",
        tokenType = "MAIN_COIN",
        iconSuffix = "${BASE_URL}icons/matic.png",
        isActive = 0
    ),
    Coin(
        coinSlug = "near-main",
        coinSymbol = "NEAR",
        coinName = "NEAR",
        decimal = 24,
        displayDecimal = 8,
        accentColor = "#00FFD4",
        tokenType = "MAIN_COIN",
        iconSuffix = "${BASE_URL}icons/near.png",
        isActive = 0
    ),
    Coin(
        coinSlug = "sol-main",
        coinSymbol = "SOL",
        coinName = "Solana",
        decimal = 9,
        displayDecimal = 8,
        accentColor = "#00FFD4",
        tokenType = "MAIN_COIN",
        iconSuffix = "${BASE_URL}icons/compound.png",
        isActive = 0
    ),
    Coin(
        coinSlug = "uni-erc20",
        coinSymbol = "UNI",
        coinName = "Uniswap",
        decimal = 18,
        displayDecimal = 8,
        accentColor = "#EC407A",
        tokenType = "ERC20TOKEN",
        iconSuffix = "${BASE_URL}icons/compound.png",
        isActive = 0
    )
)