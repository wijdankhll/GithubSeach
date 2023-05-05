package com.example.githubseach.API

import com.example.githubseach.ItemsItem
import com.example.githubseach.Response.DetailUserResponse

import com.example.githubseach.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<ItemsItem>>
}