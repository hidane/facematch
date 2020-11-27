package com.android.facematch.data.model

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import kotlin.random.Random

/**
 * Created by Abhishek.s on 26,November,2020
 */

open class FavouriteUser(
        @PrimaryKey
        @Nullable
        var userId: String? = null,

        var name: String? = null,

        var dob: String? = null,

        var phone: String? = null,

        var picture: String? = null
): RealmObject()