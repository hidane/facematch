package com.android.facematch.data.api

import com.android.facematch.data.model.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Abhishek.s on 11,November,2020
 */
interface ApiInterface {

    @GET("0.4/?randomapi")
    fun getUsers(): Call<Users>
}