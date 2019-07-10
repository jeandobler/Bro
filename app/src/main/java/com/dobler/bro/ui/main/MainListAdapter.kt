package com.dobler.bro.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dobler.bro.databinding.ListUsersBinding
import com.dobler.bro.vo.User
import kotlinx.android.synthetic.main.list_users.view.*

class MainListAdapter(private val onClick: (User) -> Unit) :
    RecyclerView.Adapter<MainListAdapter.MainListViewHolder>() {

    private var mUserList: List<User>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemBinding = ListUsersBinding.inflate(layoutInflater, parent, false)

        return MainListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {

        val mUserList1 = mUserList

        if (mUserList1 != null) {

            val user = mUserList1[position]
            val userImage = user.avatar

            Glide.with(holder.itemView.context)
                .load(userImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.itemView.ivAvatar)

            holder.bind(user)
            holder.itemView.setOnClickListener(View.OnClickListener {
                onClick(user)
            })


        }
    }

    override fun getItemCount(): Int {
        return if (mUserList == null) 0 else mUserList!!.size
    }

    fun setData(UserList: List<User>) {
        mUserList = UserList
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        mUserList = null
        notifyDataSetChanged()
    }

    inner class MainListViewHolder(private val binding: ListUsersBinding) : RecyclerView.ViewHolder(binding.getRoot()) {

        fun bind(item: User) {
            binding.setObj(item)
            binding.executePendingBindings()
        }
    }
}