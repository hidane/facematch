package com.android.facematch.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Abhishek.s on 21,November,2020
 */
data class Users (
    @SerializedName("results")
    var results: List<Result>? = null
)