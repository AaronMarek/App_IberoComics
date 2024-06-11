package com.example.ibericomics.api

import com.example.ibericomics.models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceInterface {

    @POST("login")
    fun login(@Body user: User): Call<LoginResponse>

    @POST("api/users/register")
    fun register(@Body user: User): Call<String>

    @PUT("api/users/change-password")
    fun changePassword(@Body changePasswordRequest: ChangePasswordRequest): Call<String>

    @GET("api/chapters/{comicId}")
    fun getChapters(@Path("comicId") comicId: Int): Call<List<Chapter>>

    @GET("api/comics/{userId}")
    fun getComics(@Path("userId") userId: Int): Call<List<Comic>>

    @GET("api/comics")
    fun getAllComics(): Call<List<Comic>>

    @GET("api/pages/{chapterId}")
    fun getPages(@Path("chapterId") chapterId: Int): Call<List<Page>>
}
