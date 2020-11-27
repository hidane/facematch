package com.android.facematch.ui.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.facematch.R
import com.android.facematch.data.api.ApiClient
import com.android.facematch.data.db.LocalDb
import com.android.facematch.data.model.FavouriteUser
import com.android.facematch.data.model.User
import com.android.facematch.data.model.Users
import com.android.facematch.ui.adapter.FavouriteUsersAdapter
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder
import kotlinx.android.synthetic.main.activity_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random


/**
 * Created by Abhishek.s on 21,November,2020
 */

class UserActivity : AppCompatActivity(), MyCardView.SwipeListener {

    var currentUser: User? = null
    var favUserAdapter: FavouriteUsersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setUpCardView()
        fetchUsers()
        setUpRecycler()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater;
        inflater.inflate(R.menu.favourite_menu, menu);
        return true;

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id: Int = item.itemId
        return if (id == R.id.menu_favourite) {
            if (rv_favourite.visibility == View.GONE) {
                item.setIcon(R.drawable.ic_favorite_selected)
                rv_favourite.visibility = View.VISIBLE
                sp_view.visibility = View.GONE
                favUserAdapter?.updateData(LocalDb.fetchFavouriteUsers())
                favUserAdapter?.notifyDataSetChanged()
            } else {
                item.setIcon(R.drawable.ic_favorite)
                rv_favourite.visibility = View.GONE
                sp_view.visibility = View.VISIBLE
                fetchUsers()
            }
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun setUpRecycler() {
        rv_favourite.layoutManager = LinearLayoutManager(this)

        favUserAdapter = FavouriteUsersAdapter(ArrayList(), this)

        rv_favourite.addItemDecoration(
            DividerItemDecoration(
                rv_favourite.context,
                (rv_favourite.layoutManager as LinearLayoutManager).orientation
            )
        )

        rv_favourite.adapter = favUserAdapter
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

                    currentUser = it.results?.get(0)?.user

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
                Log.e("Users Fetch Error", t.toString())
            }
        })
    }

    override fun onSwipedIn() {
        currentUser?.let {
            LocalDb.insertUserIntoFavourites(
                FavouriteUser(
                    Random.nextInt().toString(),
                    currentUser?.name?.first + " " + currentUser?.name?.last,
                    currentUser?.dob,
                    currentUser?.phone,
                    currentUser?.picture
                )
            )
        }
        fetchUsers()
    }

    override fun onSwipedOut() {
        fetchUsers()
    }
}