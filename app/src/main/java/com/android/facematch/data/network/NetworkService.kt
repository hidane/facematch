package com.android.facematch.data.network

import com.android.facematch.data.model.Users
import io.reactivex.Single

/**
 * Created by Abhishek.s on 26,October,2020
 */
interface NetworkService {
    fun getUsers(): Single<Users>
}