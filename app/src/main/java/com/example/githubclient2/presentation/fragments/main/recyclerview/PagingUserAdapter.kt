package com.example.githubclient2.presentation.fragments.main.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubclient2.R
import com.example.githubclient2.domain.model.DomainUserModel

class PagingUserAdapter :
    PagingDataAdapter<DomainUserModel, PagingUserAdapter.PagingUserViewHolder>(UserComparator) {

    class PagingUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatar: ImageView = itemView.findViewById(R.id.avatar_imageview)
        private val name: TextView = itemView.findViewById(R.id.username_textview)
        private val id: TextView = itemView.findViewById(R.id.id_textview)

        fun bind(item: DomainUserModel) {
            if (item.avatar == null) {
                avatar.setImageResource(R.drawable.github_logo)
            } else {
                Glide.with(itemView.context).load(item.avatar).placeholder(R.drawable.github_logo).into(avatar)
            }
            name.text = item.login ?: "Unknown"
            id.text = item.id.toString()
        }

        companion object {
            fun from(parent: ViewGroup): PagingUserViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.user_recycler_view_item, parent, false)
                return PagingUserViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: PagingUserViewHolder, position: Int) {
        getItem(position)?.let {holder.bind(it)}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingUserViewHolder {
        return PagingUserViewHolder.from(parent)
    }

    object UserComparator : DiffUtil.ItemCallback<DomainUserModel>() {
        override fun areItemsTheSame(oldItem: DomainUserModel, newItem: DomainUserModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: DomainUserModel, newItem: DomainUserModel) =
            oldItem == newItem
    }


}