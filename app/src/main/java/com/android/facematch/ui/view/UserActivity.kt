package com.android.facematch.ui.view

import UserViewModel
import ViewModelFactory
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.android.facematch.R
import com.android.facematch.data.network.NetworkDao
import com.android.facematch.data.network.NetworkImpl
import com.android.facematch.utils.Status
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder
import kotlinx.android.synthetic.main.activity_user.*


/**
 * Created by Abhishek.s on 21,November,2020
 */

class UserActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setUpCardView()
        setupViewModel()
        setupObserver()
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

    private fun setupObserver() {
        userViewModel.getUsers().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    pb_loader.visibility = View.GONE
                    it.data?.let {
                        sp_view.addView(it.results?.get(0)?.user?.let { it1 ->
                            MyCardView(
                                applicationContext,
                                it1, sp_view
                            )
                        })
                    }
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    //Handle Error
                    pb_loader.visibility = View.GONE
                }
            }
        })
    }


    private fun setupViewModel() {
        userViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(NetworkDao(NetworkImpl()))
        ).get(UserViewModel::class.java)
    }
}