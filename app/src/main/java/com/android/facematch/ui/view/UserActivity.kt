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
import kotlinx.android.synthetic.main.activity_user.*

/**
 * Created by Abhishek.s on 21,November,2020
 */

class UserActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        btn_sample.setOnClickListener(this)
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
                    pb_loader.visibility = View.GONE
                    Toast.makeText(this, it.data.toString(), Toast.LENGTH_LONG).show()
                    btn_sample.visibility = View.VISIBLE
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    //Handle Error
                    pb_loader.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    btn_sample.visibility = View.VISIBLE
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

    override fun onClick(p0: View?) {
        when (p0) {
            btn_sample -> {
                pb_loader.visibility = View.VISIBLE
                btn_sample.visibility = View.GONE
                userViewModel.fetchUsers()
            }
        }
    }
}