package com.android.facematch.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Abhishek.s on 21,November,2020
 */

data class Name(
    @SerializedName("title")
    var title: String? = null,

    @SerializedName("first")
    var first: String? = null,

    @SerializedName("last")
    var last: String? = null
)
