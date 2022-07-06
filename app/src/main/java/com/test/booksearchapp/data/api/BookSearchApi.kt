package com.test.booksearchapp.data.api

import com.test.booksearchapp.data.model.SearchResponse
import com.test.booksearchapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/*
 retrofit은 interface를 통해 데이터에 접근함
 */

interface BookSearchApi {
    // 요청의 헤더를 추가
    @Headers("Authorization: KakaoAK ${Constants.API_KEY}")
    // 데이터 조회 용도, URL에 데이터를 모두 담아서 전송, base URL이 앞에 붙음
    @GET("/v3/search/book")
    suspend fun searchBooks(
        // @Query - URI에 파라메터(쿼리스트링)를 추가해서 보낼 수 있도록 해주는 Annotation, 아래 api 페이지 참조
        // https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<SearchResponse>
}