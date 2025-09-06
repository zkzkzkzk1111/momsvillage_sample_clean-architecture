package com.ez.remote.api

import com.ez.remote.api.interceptor.RequestHeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30

fun createApiService(baseUrl: String): ApiService {
    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val okHttpClient = OkHttpClient.Builder().apply {
        readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        writeTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(logging)
        addNetworkInterceptor(RequestHeaderInterceptor())

    }.build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

}