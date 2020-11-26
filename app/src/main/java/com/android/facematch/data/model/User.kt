package com.android.facematch.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

/**
 * Created by Abhishek.s on 21,November,2020
 */

data class User(

    @PrimaryKey
    @Required
    var userId: String? = null,

    @SerializedName("gender")
    var gender: String? = null,

    @SerializedName("name")
    var name: Name? = null,

    @SerializedName("location")
    var location: Location? = null,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("username")
    var username: String? = null,

    @SerializedName("password")
    var password: String? = null,

    @SerializedName("salt")
    var salt: String? = null,

    @SerializedName("md5")
    var md5: String? = null,

    @SerializedName("sha1")
    var sha1: String? = null,

    @SerializedName("sha256")
    var sha256: String? = null,

    @SerializedName("registered")
    var registered: String? = null,

    @SerializedName("dob")
    var dob: String? = null,

    @SerializedName("phone")
    var phone: String? = null,

    @SerializedName("cell")
    var cell: String? = null,

    @SerializedName("SSN")
    var sSN: String? = null,

    @SerializedName("picture")
    var picture: String? = null
) : RealmObject() {
    init {
        userId = Random().nextInt(50000).toString() + ""
    }
}