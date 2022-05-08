package com.example.apiapp.crypto.api

import com.google.gson.annotations.SerializedName

data class CryptoListItem(
    @SerializedName("1d")
    val day: D,
    @SerializedName("30d")
    val month: DX,
    @SerializedName("365d")
    val year: DXX,
    @SerializedName("7d")
    val week: DXXX,
    val circulating_supply: String,
    val currency: String,
    val first_candle: String,
    val first_order_book: String,
    val first_priced_at: String,
    val first_trade: String,
    val high: String,
    val high_timestamp: String,
    val id: String,
    val logo_url: String,
    val market_cap: String,
    val market_cap_dominance: String,
    val max_supply: String,
    val name: String,
    val num_exchanges: String,
    val num_pairs: String,
    val num_pairs_unmapped: String,
    val platform_currency: String,

    val price: String,
    val price_date: String,
    val price_timestamp: String,
    val rank: String,
    val rank_delta: String,
    val status: String,
    val symbol: String,
    val ytd: Ytd

)


