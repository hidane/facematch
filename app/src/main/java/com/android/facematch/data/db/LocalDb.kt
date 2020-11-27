package com.android.facematch.data.db

import com.android.facematch.data.model.FavouriteUser
import com.android.facematch.data.model.User
import io.realm.Realm
import io.realm.RealmResults

/**
 * Created by Abhishek.s on 26,November,2020
 */


object LocalDb {

    fun insertUserIntoFavourites(user: FavouriteUser) {

        val realm = Realm.getDefaultInstance()

        realm.executeTransaction { realm ->
            realm.insertOrUpdate(user)
        }
    }

    fun fetchFavouriteUsers(): ArrayList<FavouriteUser> {

        val realm = Realm.getDefaultInstance()

        val results: RealmResults<FavouriteUser> =
            realm.where(FavouriteUser::class.java)
                .findAll()

        return ArrayList(results)
    }
}