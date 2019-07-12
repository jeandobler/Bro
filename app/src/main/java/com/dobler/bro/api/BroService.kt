package com.dobler.bro.api

import com.dobler.bro.vo.User
import retrofit2.http.*

interface BroService {

    @GET("users")
    suspend fun getUser(
        @Query("email") email: String?,
        @Query("password") password: String?
    ): List<User>

    @FormUrlEncoded
    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Field("bro") bro: Int?): User

}