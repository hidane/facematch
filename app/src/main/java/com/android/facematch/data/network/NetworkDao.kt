package com.android.facematch.data.network

/**
 * Created by Abhishek.s on 21,November,2020
 */

class NetworkDao(private val networkService: NetworkService) {

    fun getUsers() = networkService.getUsers()
}