package com.mobiaxe.core.remote.service

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object WASDServiceFactory {
    fun makeWASDService(isDebug: Boolean, baseUrl: String, apiKey: String): WASDService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(makeOkHttpClient(
                makeLoggingInterceptor(isDebug), apiKey))
            .addConverterFactory(GsonConverterFactory.create(makeGson()))
            .build()
        return retrofit.create(WASDService::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, apiKey: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(makeHeaderInterceptor(apiKey))
            .addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    private fun makeGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }

    private fun makeHeaderInterceptor(apiKey: String): Interceptor =
        Interceptor { chain ->
            val request = chain.request().newBuilder()
            request.addHeader("user-key", apiKey)
            chain.proceed(request.build())
        }
}