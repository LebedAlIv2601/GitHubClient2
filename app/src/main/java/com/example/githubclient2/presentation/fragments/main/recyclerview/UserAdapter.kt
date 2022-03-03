package com.example.githubclient2.presentation.fragments.main.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubclient2.R
import com.example.githubclient2.domain.model.DomainUserModel

class UserAdapter(private val users: MutableList<DomainUserModel>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatar: ImageView = itemView.findViewById(R.id.avatar_imageview)
        private val name: TextView = itemView.findViewById(R.id.username_textview)
        private val id: TextView = itemView.findViewById(R.id.id_textview)

        fun bind(item: DomainUserModel) {
            if (item.avatar == null) {
                avatar.setImageResource(R.drawable.github_logo)
            } else {
                Glide.with(itemView.context).load(item.avatar).into(avatar)
            }
            name.text = item.login ?: "Unknown"
            id.text = item.id.toString()
        }

        companion object {
            fun from(parent: ViewGroup): UserViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.user_recycler_view_item, parent, false)
                return UserViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun addUsers(users: MutableList<DomainUserModel>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }
}