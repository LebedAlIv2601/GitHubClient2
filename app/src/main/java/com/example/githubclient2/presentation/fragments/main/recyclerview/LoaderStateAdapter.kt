package com.example.githubclient2.presentation.fragments.main.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubclient2.R

import com.example.githubclient2.databinding.LoadStateItemBinding

class LoaderStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>()  {

    class LoaderViewHolder(private val binding: LoadStateItemBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root){


        companion object {
            fun from(parent: ViewGroup, retry: () -> Unit): LoaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                return LoaderViewHolder(LoadStateItemBinding.bind(layoutInflater
                    .inflate(R.layout.load_state_item, parent, false)), retry)
            }
        }

        init {
            binding.buttonRetry.setOnClickListener {
                retry()
            }
        }

        fun bind(loadState: LoadState){
            with(binding){
                buttonRetry.isVisible = loadState is LoadState.Error
                textViewRetry.isVisible = loadState is LoadState.Error
                progressBarUserList.isVisible = loadState is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) = holder.bind(loadState)


    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        return LoaderViewHolder.from(parent, retry)
    }

}