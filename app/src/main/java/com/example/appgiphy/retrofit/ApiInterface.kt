package com.example.appgiphy.retrofit

import com.example.appgiphy.model.Gifs
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    //trending
    @GET("v1/gifs/trending")
    fun getGifs(
        @Query("api_key") api_key: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String
    ): Call<Gifs>

    //search
    @GET("v1/gifs/search")
    fun getGifsSearch(
        @Query("api_key") api_key: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("q") subject: String
    ): Call<Gifs>

}