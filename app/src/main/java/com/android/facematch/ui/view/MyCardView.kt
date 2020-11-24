package com.android.facematch.ui.view

import com.android.facematch.R
import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import com.android.facematch.data.model.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.NonReusable
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import com.mindorks.placeholderview.annotations.swipe.*


/**
 * Created by Abhishek.s on 24,November,2020
 */
@NonReusable
@Layout(R.layout.layout_card)
class MyCardView(context: Context, user: User, swipeView: SwipePlaceHolderView) {

    @View(R.id.civ_profile_image)
    var profileImageView: ImageView? = null

    @View(R.id.atv_title)
    var title: AppCompatTextView? = null

    @View(R.id.atv_description)
    var description: AppCompatTextView? = null


    var user: User = user
    var mContext: Context = context
    var mSwipeView: SwipePlaceHolderView = swipeView

    @Resolve
    fun onResolved() {
        profileImageView?.let {
            Glide.with(mContext)
                .load(user.picture?.replace("http", "https"))
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_person)
                .into(it)
        }

        title?.text = "My Name is"
        description?.text = user.name?.title + " " + user?.name?.first + " " + user?.name?.last
    }

    @SwipeOut
    fun onSwipedOut() {
        Log.d("EVENT", "onSwipedOut")
        mSwipeView.addView(this)
    }

    @SwipeCancelState
    fun onSwipeCancelState() {
        Log.d("EVENT", "onSwipeCancelState")
    }

    @SwipeIn
    private fun onSwipeIn() {
        Log.d("EVENT", "onSwipedIn")
    }

    @SwipeInState
   fun onSwipeInState() {
        Log.d("EVENT", "onSwipeInState")
    }

    @SwipeOutState
    fun onSwipeOutState() {
        Log.d("EVENT", "onSwipeOutState")
    }
}