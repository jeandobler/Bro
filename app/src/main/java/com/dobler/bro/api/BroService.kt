package com.dobler.bro.api

import com.dobler.bro.vo.User
import retrofit2.http.GET
import retrofit2.http.Query

interface BroService {

    @GET("users")
    suspend fun getUser(
        @Query("email") email: String?,
        @Query("password") password: String?
    ): List<User>

}