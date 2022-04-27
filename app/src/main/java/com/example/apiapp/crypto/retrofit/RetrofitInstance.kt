package com.example.apiapp.crypto.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: RetroCryptoApi by lazy{
        Retrofit.Builder()
            .baseUrl("https://api.nomics.com/v1/currencies/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetroCryptoApi::class.java)
        
    }
}