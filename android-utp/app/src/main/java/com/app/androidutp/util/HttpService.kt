package com.app.androidutp.util

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpService {

    @Volatile
    private var baseUrl: String = "https://jsonplaceholder.typicode.com/"

    @Volatile
    private var retrofit: Retrofit = buildRetrofit(baseUrl)

    private fun buildRetrofit(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun setBaseUrl(url: String) {
        val normalized = if (url.endsWith("/")) url else "$url/"
        if (normalized != baseUrl) {
            baseUrl = normalized
            retrofit = buildRetrofit(baseUrl)
        }
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

    inline fun <reified T> create(): T = create(T::class.java)

    fun <T> createWithBaseUrl(tempBaseUrl: String, service: Class<T>): T {
        val normalized = if (tempBaseUrl.endsWith("/")) tempBaseUrl else "$tempBaseUrl/"
        val r = buildRetrofit(normalized)
        return r.create(service)
    }

    inline fun <reified T> createWithBaseUrl(tempBaseUrl: String): T = createWithBaseUrl(tempBaseUrl, T::class.java)
}