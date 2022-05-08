package com.example.apiapp.crypto.retrofit

import com.example.apiapp.crypto.api.CryptoListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroCryptoApi {
    @GET("ticker") //base URL utáni endpoin
    suspend fun getCryptos(@Query("key") key: String = "88a8c4029de4030e40694539255d448596c4f110"): Response<List<CryptoListItem>>


}