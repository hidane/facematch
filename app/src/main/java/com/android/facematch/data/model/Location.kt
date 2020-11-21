package com.android.facematch.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Abhishek.s on 21,November,2020
 */

data class Location(
    @SerializedName("street")
    var street: String? = null,

    @SerializedName("city")
    var city: String? = null,

    @SerializedName("state")
    var state: String? = null,

    @SerializedName("zip")
    var zip: String? = null
)