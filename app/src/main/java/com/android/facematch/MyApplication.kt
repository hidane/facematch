package com.android.facematch

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration




/**
 * Created by Abhishek.s on 26,November,2020
 */
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
            .name("favourites.realm")
            .schemaVersion(0)
            .build()
        Realm.setDefaultConfiguration(realmConfig)
    }

}