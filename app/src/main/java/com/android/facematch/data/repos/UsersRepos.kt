package com.android.facematch.data.repos

import com.android.facematch.data.model.Users
import com.android.facematch.data.network.NetworkDao
import io.reactivex.Single

/**
 * Created by Abhishek.s on 26,October,2020
 */
class UsersRepos (public val networkDao: NetworkDao) {

    fun getUsers() : Single<Users> {
        return networkDao.getUsers()
    }
}