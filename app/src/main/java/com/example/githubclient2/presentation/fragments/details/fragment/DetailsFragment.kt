package com.example.githubclient2.presentation.fragments.details.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.githubclient2.R
import com.example.githubclient2.databinding.FragmentDetailsBinding
import com.example.githubclient2.domain.model.DomainUserInfoModel
import com.example.githubclient2.presentation.fragments.details.vm.DetailsViewModel
import com.example.githubclient2.utils.FragmentTitleResource
import com.example.githubclient2.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsFragment : Fragment() {


    private val vm: DetailsViewModel by viewModel()
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var userLogin: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_details, container, false)

        userLogin = if(arguments != null)
            requireArguments().getString("bundleUserKey", "") else ""

        FragmentTitleResource.title = userLogin

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

        setupObservers()
    }

    private fun setupListeners() {
        with(binding){
            retryFragmentDetailsButton.setOnClickListener {
                setupObservers()
            }
        }
    }

    private fun setupObservers() {
        vm.getUserInfo(userLogin).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status){
                    Status.SUCCESS -> {
                        Log.e("Loading", "Status success")
                        with(binding) {
                            detailsProgressBar.visibility = View.GONE
                            infoLayout.visibility = View.VISIBLE
                            retryFragmentDetailsButton.visibility = View.GONE
                            retryFragmentDetailsTextView.visibility = View.GONE
                            if(resource.data != null) {
                                val user: DomainUserInfoModel = resource.data
                                if (user.avatar != null){
                                Glide.with(binding.root.context).load(user.avatar)
                                    .placeholder(R.drawable.github_logo).into(avatarDetailsImageView)
                                } else {
                                    avatarDetailsImageView.setImageResource(R.drawable.github_logo)
                                }
                                binding.user = user
                            }
                        }
                    }
                    Status.ERROR -> {
                        Log.e("Loading", "Status error")
                        with(binding) {
                            detailsProgressBar.visibility = View.GONE
                            nameDetails.text = userLogin
                            avatarDetailsImageView.setImageResource(R.drawable.github_logo)
                            infoLayout.visibility = View.GONE
                            retryFragmentDetailsButton.visibility = View.VISIBLE
                            retryFragmentDetailsTextView.visibility = View.VISIBLE
                        }
                    }
                    Status.LOADING -> {
                        Log.e("Loading", "Status loading")
                        with(binding) {
                            detailsProgressBar.visibility = View.VISIBLE
                            nameDetails.text = userLogin
                            infoLayout.visibility = View.GONE
                            retryFragmentDetailsButton.visibility = View.GONE
                            retryFragmentDetailsTextView.visibility = View.GONE
                        }
                    }
                }
            }
        })
    }

}