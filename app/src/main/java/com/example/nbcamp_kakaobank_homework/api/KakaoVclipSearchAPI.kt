package com.example.nbcamp_kakaobank_homework.api

import com.example.nbcamp_kakaobank_homework.image_data.ImageSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoVclipSearchAPI {
    @GET("v2/search/vclip")
    suspend fun getVclipSearchKeyword(
        @Header("Authorization") apiKey: String,
        @Query("query") query: String,
        @Query("sort") sort: String,
//            @Query("page") page: Int,
//            @Query("size") size: Int

    ): Response<ImageSearch>
}

object VclipAPI_info {
    const val BASE_URL = "https://dapi.kakao.com"
    const val API_KEY = "KakaoAK a2d65d8241aa41cd3770c856f5d19300"
}