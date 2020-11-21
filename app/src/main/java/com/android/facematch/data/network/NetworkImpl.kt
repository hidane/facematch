package com.android.facematch.data.network

import com.android.facematch.data.model.Users
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class NetworkImpl : NetworkService {

    override fun getUsers(): Single<Users> {
        return Rx2AndroidNetworking.get("https://randomuser.me/api/0.4/?randomapi")
            .build()
            .getObjectSingle(Users::class.java)
    }
}