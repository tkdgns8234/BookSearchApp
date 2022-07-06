package com.test.booksearchapp.util

import com.test.booksearchapp.BuildConfig

object Constants {
    // rest api에 사용될 base url
    const val BASE_URL = "https://dapi.kakao.com/"

    // api key 의 경우 외부에 노출되면 안되기 때문에 local.properties 파일에 추가
    const val API_KEY = BuildConfig.bookApiKey
}