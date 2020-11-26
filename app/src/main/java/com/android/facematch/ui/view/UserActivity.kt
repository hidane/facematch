package com.android.facematch.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.facematch.R
import com.android.facematch.data.api.ApiClient
import com.android.facematch.data.model.Users
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder
import kotlinx.android.synthetic.main.activity_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Abhishek.s on 21,November,2020
 */

class UserActivity : AppCompatActivity(), MyCardView.SwipeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setUpCardView()
        fetchUsers()
    }

    private fun setUpCardView() {
        sp_view.getBuilder<SwipePlaceHolderView, SwipeViewBuilder<SwipePlaceHolderView>>()
            .setDisplayViewCount(1)
            .setSwipeDecor(
                SwipeDecor()
                    .setPaddingTop(20)
                    .setRelativeScale(0.01f)
                    .setSwipeInMsgLayoutId(R.layout.layout_swipe_out)
                    .setSwipeOutMsgLayoutId(R.layout.layout_swipe_in)
            )
    }

    private fun fetchUsers() {

        pb_loader.visibility = View.VISIBLE

        val call: Call<Users> = ApiClient.getClient.getUsers()

        call.enqueue(object : Callback<Users> {

            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                pb_loader.visibility = View.GONE

                response.body()?.let {
                    sp_view.addView(it.results?.get(0)?.user?.let { it1 ->
                        MyCardView(
                            applicationContext,
                            it1, sp_view, this@UserActivity
                        )
                    })
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable?) {
                pb_loader.visibility = View.GONE
                Log.e("Places Api Error", t.toString())
            }
        })

    }

    override fun onSwipedIn() {
        fetchUsers()
    }

    override fun onSwipedOut() {
        fetchUsers()
    }
}