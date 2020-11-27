package com.android.facematch.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.facematch.R
import com.android.facematch.data.model.FavouriteUser
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_favourite.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Abhishek.s on 26,October,2020
 */
class FavouriteUsersAdapter(
    private val users: ArrayList<FavouriteUser>, private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_favourite, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserViewHolder).bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun updateData(list: List<FavouriteUser>) {
        users.clear()
        users.addAll(list)
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: FavouriteUser) {
            itemView.atv_user_name.text = context.getString(R.string.item_name, user.name)

            user.picture?.let {
                Glide.with(context)
                    .load(user.picture?.replace("http", "https"))
                    .apply(RequestOptions.circleCropTransform())
                    .placeholder(R.drawable.ic_person)
                    .into(itemView.iv_user_pic)
            }

            val date = user.dob?.toLong()?.times(1000L)?.let { Date(it) }
            // format of the date
            // format of the date
            val jdf = SimpleDateFormat("dd-MM-yyyy")
            jdf.timeZone = TimeZone.getTimeZone("GMT-4")
            itemView.atv_dob.text = context.getString(R.string.item_dob, jdf.format(date))

            itemView.atv_phone.text = context.getString(R.string.item_phone, user.phone)
        }
    }
}