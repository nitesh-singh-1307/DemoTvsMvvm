package com.demo.demotvsmvvm.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestClientApi {

    private val httpClient : OkHttpClient = OkHttpClient.Builder()

        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY  })
        .connectTimeout(30 , TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()


    fun getClient() : ApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClient).build()
        return retrofit.create(ApiInterface::class.java)

    }
}