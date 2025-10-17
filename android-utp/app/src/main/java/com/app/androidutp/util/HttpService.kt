package com.app.androidutp.util

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Simple Retrofit provider for the app.
 * - Uses OkHttp logging interceptor (BODY) while keeping it easy to turn off in production
 * - Reasonable default timeouts
 * - Allows setting a base URL before creating services
 *
 * Usage:
 * HttpService.setBaseUrl("https://api.example.com/")
 * val api = HttpService.create(MyApi::class.java)
 * // or with the reified helper:
 * val api2 = HttpService.create<MyApi>()
 */
object HttpService {

    // Default placeholder. Set a real URL during app startup (e.g., in Application or before first call).
    @Volatile
    private var baseUrl: String = "https://jsonplaceholder.typicode.com/"

    @Volatile
    private var retrofit: Retrofit = buildRetrofit(baseUrl)

    private fun buildRetrofit(url: String): Retrofit {
        // Logging interceptor (useful during development)
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .callTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Replace the base URL and rebuild the Retrofit instance.
     * Call this early (before creating services) or when you need to switch endpoints.
     */
    fun setBaseUrl(url: String) {
        val normalized = if (url.endsWith("/")) url else "$url/"
        if (normalized != baseUrl) {
            baseUrl = normalized
            retrofit = buildRetrofit(baseUrl)
        }
    }

    /**
     * Create a Retrofit service of the given type using the current Retrofit instance.
     * Non-inline version (uses Class<T>) to avoid Kotlin inline visibility rules.
     */
    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

    /**
     * Reified convenience wrapper that delegates to the non-inline create(Class).
     */
    inline fun <reified T> create(): T = create(T::class.java)

    /**
     * Create a service using a temporary base URL (does not change the global one).
     * Non-inline version that accepts Class<T>.
     */
    fun <T> createWithBaseUrl(tempBaseUrl: String, service: Class<T>): T {
        val normalized = if (tempBaseUrl.endsWith("/")) tempBaseUrl else "$tempBaseUrl/"
        val r = buildRetrofit(normalized)
        return r.create(service)
    }

    /**
     * Reified convenience wrapper for createWithBaseUrl.
     */
    inline fun <reified T> createWithBaseUrl(tempBaseUrl: String): T = createWithBaseUrl(tempBaseUrl, T::class.java)
}