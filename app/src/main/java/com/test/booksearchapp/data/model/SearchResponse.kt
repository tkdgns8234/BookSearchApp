package com.test.booksearchapp.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/*
해당 코드는 Json to kotlin class 라는 플러그인으로 자동 생성된 코드
moshi 컨버터가 Json 데이터를 객체화 시켜준다.
아래는 moshi Annotation을 사용한 코드!
 */

// JsonClass는 Json Object에 대응한다. Json Object에 대응하는 클래스를 만들 때 사용한다.
@JsonClass(generateAdapter = true)
data class SearchResponse(
    // DTO(데이터 전송을 위한 객체)의 변수명을 다른것으로 설정해도
    // 아래 어노테이션의 name 값을 키값으로해서 Json 데이터를 가져온다.
    @field:Json(name = "documents")
    val documents: List<Book>,
    @field:Json(name = "meta")
    val meta: Meta
)