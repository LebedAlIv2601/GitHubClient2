package com.example.githubclient2.presentation.fragments.main.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.githubclient2.R
import com.example.githubclient2.databinding.UserRecyclerViewItemBinding
import com.example.githubclient2.domain.model.DomainUserModel

class PagingUserAdapter(private val clickListener: OnUserClickListener) :
    PagingDataAdapter<DomainUserModel, PagingUserAdapter.PagingUserViewHolder>(UserComparator) {

    interface OnUserClickListener{
        fun onUserClick(user: DomainUserModel, position: Int)
    }

    class PagingUserViewHolder(private val binding: UserRecyclerViewItemBinding,
                               private val clickListenerIn: OnUserClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DomainUserModel) {
            binding.apply {
                if (item.avatar == null) {
                    avatarImageview.setImageResource(R.drawable.github_logo)
                } else {
                    if(item.id <= 100) {
                        Glide.with(binding.root.context).load(item.avatar)
                            .placeholder(R.drawable.github_logo).into(avatarImageview)
                    } else {
                        Glide.with(binding.root.context).load(item.avatar)
                            .apply(RequestOptions.skipMemoryCacheOf(true)
                                .diskCacheStrategy(DiskCacheStrategy.NONE))
                            .placeholder(R.drawable.github_logo).into(avatarImageview)
                    }
                }
                usernameTextview.text = item.login ?: "Unknown"
                idTextview.text = item.id.toString()

                itemView.setOnClickListener {
                    clickListenerIn.onUserClick(item, absoluteAdapterPosition)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup, listener: OnUserClickListener): PagingUserViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.user_recycler_view_item, parent, false)
                return PagingUserViewHolder(UserRecyclerViewItemBinding.bind(view), listener)
            }
        }
    }

    override fun onBindViewHolder(holder: PagingUserViewHolder, position: Int) {
        getItem(position)?.let {holder.bind(it)}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingUserViewHolder {
        return PagingUserViewHolder.from(parent, clickListener)
    }

    object UserComparator : DiffUtil.ItemCallback<DomainUserModel>() {
        override fun areItemsTheSame(oldItem: DomainUserModel, newItem: DomainUserModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: DomainUserModel, newItem: DomainUserModel) =
            oldItem == newItem
    }


}