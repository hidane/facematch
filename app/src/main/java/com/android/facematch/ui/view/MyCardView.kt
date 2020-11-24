package com.android.facematch.ui.view

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import com.android.facematch.R
import com.android.facematch.data.model.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.NonReusable
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import com.mindorks.placeholderview.annotations.swipe.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Abhishek.s on 24,November,2020
 */
@NonReusable
@Layout(R.layout.layout_card)
class MyCardView(context: Context, user: User, swipeView: SwipePlaceHolderView, swipeListener: SwipeListener) :
    android.view.View.OnClickListener {

    interface SwipeListener {
        fun onSwipedIn()
        fun onSwipedOut()
    }

    @View(R.id.civ_profile_image)
    var profileImageView: ImageView? = null

    @View(R.id.atv_title)
    var title: AppCompatTextView? = null

    @View(R.id.atv_description)
    var description: AppCompatTextView? = null

    @View(R.id.ib_persons)
    var personMenu: AppCompatImageButton? = null

    @View(R.id.ib_dob)
    var dobMenu: AppCompatImageButton? = null

    @View(R.id.ib_address)
    var addressMenu: AppCompatImageButton? = null

    @View(R.id.ib_contact)
    var contactMenu: AppCompatImageButton? = null

    var user: User = user
    var mContext: Context = context
    var mSwipeView: SwipePlaceHolderView = swipeView
    var dob: String? = null
    var swipeListener: SwipeListener? = swipeListener

    @Resolve
    fun onResolved() {
        profileImageView?.let {
            Glide.with(mContext)
                .load(user.picture?.replace("http", "https"))
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_person)
                .into(it)
        }

        convertDate()

        personMenu?.setOnClickListener(this)
        dobMenu?.setOnClickListener(this)
        addressMenu?.setOnClickListener(this)
        contactMenu?.setOnClickListener(this)

        personMenu?.performClick()
    }

    fun convertDate() {
        val date = user.dob?.toLong()?.times(1000L)?.let { Date(it) }
        // format of the date
        // format of the date
        val jdf = SimpleDateFormat("dd-MM-yyyy")
        jdf.timeZone = TimeZone.getTimeZone("GMT-4")
        dob = jdf.format(date)
    }

    @SwipeOut
    fun onSwipedOut() {
        Log.d("EVENT", "onSwipedOut")
        swipeListener?.onSwipedIn()
    }

    @SwipeCancelState
    fun onSwipeCancelState() {
        Log.d("EVENT", "onSwipeCancelState")
    }

    @SwipeIn
    private fun onSwipeIn() {
        Log.d("EVENT", "onSwipedIn")
        swipeListener?.onSwipedOut()
    }

    @SwipeInState
    fun onSwipeInState() {
        Log.d("EVENT", "onSwipeInState")
    }

    @SwipeOutState
    fun onSwipeOutState() {
        Log.d("EVENT", "onSwipeOutState")
    }

    override fun onClick(p0: android.view.View?) {
        when (p0) {
            personMenu -> {
                title?.text = mContext.getString(R.string.tile_name)
                description?.text =
                    user.name?.title + " " + user?.name?.first + " " + user?.name?.last
                personMenu?.isSelected = true
                dobMenu?.isSelected = false
                addressMenu?.isSelected = false
                contactMenu?.isSelected = false
            }

            dobMenu -> {
                title?.text = mContext.getString(R.string.title_dob)
                description?.text = dob
                personMenu?.isSelected = false
                dobMenu?.isSelected = true
                addressMenu?.isSelected = false
                contactMenu?.isSelected = false
            }

            addressMenu -> {
                title?.text = mContext.getString(R.string.title_address)
                description?.text =
                    user.location?.street + "," + user.location?.city

                personMenu?.isSelected = false
                dobMenu?.isSelected = false
                addressMenu?.isSelected = true
                contactMenu?.isSelected = false
            }

            contactMenu -> {
                title?.text = mContext.getString(R.string.title_contact)
                description?.text =
                    mContext.getString(R.string.body_contact, user.email, user.cell, user.phone)

                personMenu?.isSelected = false
                dobMenu?.isSelected = false
                addressMenu?.isSelected = false
                contactMenu?.isSelected = true
            }
        }
    }
}