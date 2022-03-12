package com.example.githubclient2.presentation.fragments.main.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubclient2.R
import com.example.githubclient2.databinding.UserRecyclerViewItemBinding
import com.example.githubclient2.domain.model.DomainUserModel

class PagingUserAdapter :
    PagingDataAdapter<DomainUserModel, PagingUserAdapter.PagingUserViewHolder>(UserComparator) {

    class PagingUserViewHolder(private val binding: UserRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DomainUserModel) {
            binding.apply {
                if (item.avatar == null) {
                    avatarImageview.setImageResource(R.drawable.github_logo)
                } else {
                    Glide.with(binding.root.context).load(item.avatar)
                        .placeholder(R.drawable.github_logo).into(avatarImageview)
                }
                usernameTextview.text = item.login ?: "Unknown"
                idTextview.text = item.id.toString()
            }
        }

        companion object {
            fun from(parent: ViewGroup): PagingUserViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.user_recycler_view_item, parent, false)
                return PagingUserViewHolder(UserRecyclerViewItemBinding.bind(view))
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