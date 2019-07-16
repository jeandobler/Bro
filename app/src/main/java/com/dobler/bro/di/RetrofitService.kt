package com.dobler.bro.di

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitService {
    fun retrofitRequest(): Retrofit
}


// service class with injected helloModel instance
/**
 * Hello Service Impl
 * Will use HelloMessageData data
 */
class RetrofitServiceImplement(private val client: OkHttpClient, private val url: String) : RetrofitService {

    override fun retrofitRequest(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
        return retrofit.build()
    }
}