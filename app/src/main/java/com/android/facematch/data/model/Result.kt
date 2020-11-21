package com.android.facematch.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Abhishek.s on 21,November,2020
 */

data class Result (
    @SerializedName("user")
    var user: User? = null,

    @SerializedName("seed")
    var seed: String? = null,

    @SerializedName("version")
    var version: String? = null
)