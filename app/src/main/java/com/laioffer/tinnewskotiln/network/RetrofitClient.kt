package com.laioffer.tinnewskotiln.network

import android.content.Context
import com.ashokvarma.gander.GanderInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val API_KEY = "d8e76eeb861441b48dbb283fa7b3ca34"
private const val BASE_URL = "https://newsapi.org/v2/"

class RetrofitClient {

    companion object {

        fun newInstance(context: Context): Retrofit {
            val okHttpClient = OkHttpClient.Builder().addInterceptor(HeaderInterceptor())
                .addInterceptor(GanderInterceptor(context).showNotification(true)).build()
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                .build()
        }
    }

    private class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val request = original.newBuilder().header("X-Api-Key", API_KEY).build()
            return chain.proceed(request)
        }
    }
}