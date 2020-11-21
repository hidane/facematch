package com.android.facematch.ui.view

import UserViewModel
import ViewModelFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.android.facematch.R
import com.android.facematch.data.network.NetworkDao
import com.android.facematch.data.network.NetworkImpl
import com.android.facematch.utils.Status

/**
 * Created by Abhishek.s on 21,November,2020
 */

class UserActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setUpRecycler()
        setupViewModel()
        setupObserver()
    }

    private fun setUpRecycler() {

    }

    private fun setupObserver() {
        userViewModel.getUsers().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(this, it.data.toString(), Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    //Handle Error
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
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