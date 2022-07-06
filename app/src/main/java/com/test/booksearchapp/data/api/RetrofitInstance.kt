package com.test.booksearchapp.data.api

import com.test.booksearchapp.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


// object + lazy 키워드로 retrofit 객체를 싱글톤 + 참조시점에 생성되도록 함
object RetrofitInstance {

    // okHttpClient 객체 생성 (서버 클라이언트 사이에서 인터셉터 역할을 함, 필수구현은 아님)
    private val okHttpClient: OkHttpClient by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        // lazy 키워드에서 아래 okHttpClient 객체 반환
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    // retrofit 객체 생성, 필수구현
    private val retrofit: Retrofit by lazy {
        // 빌더 패턴
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    val api: BookSearchApi by lazy {
        retrofit.create(BookSearchApi::class.java)
    }
}