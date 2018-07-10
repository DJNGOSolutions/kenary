package me.djangosolutions.kenary.util


import me.djangosolutions.kenary.MainActivity
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val builder = OkHttpClient.Builder().addInterceptor { chain ->
    val request = chain
            .request().newBuilder()
            .addHeader("Authorization", MainActivity.token).build()
    chain.proceed(request)
}

private val client: OkHttpClient = builder.build()

private val retrofit = Retrofit.Builder()
        .baseUrl("http://206.189.212.245:9000/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

internal val gameNewsService = retrofit.create(AmaiService::class.java)