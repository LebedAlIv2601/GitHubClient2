package com.example.githubclient2.presentation.details.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.githubclient2.R
import com.example.githubclient2.app.appComponent
import com.example.githubclient2.databinding.FragmentDetailsBinding
import com.example.githubclient2.domain.model.DomainUserInfoModel
import com.example.githubclient2.presentation.details.vm.DetailsViewModel
import com.example.githubclient2.presentation.details.vm.DetailsViewModelFactory
import com.example.githubclient2.utils.Resource
import javax.inject.Inject


class DetailsFragment : Fragment() {


    private val vm: DetailsViewModel by viewModels {
        viewModelFactory
    }
    private var binding: FragmentDetailsBinding? = null
    private var userLogin: String? = null


    @Inject
    lateinit var viewModelFactory: DetailsViewModelFactory

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_details, container, false)

        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userLogin = if(arguments != null)
            requireArguments().getString("bundleUserKey", "") else ""

        binding?.detailsFragmentToolbar?.title = userLogin
        setupListeners()

        setupObservers()
    }

    private fun setupListeners() {

        binding?.retryFragmentDetailsButton?.setOnClickListener {
            setupObservers()
        }

        binding?.detailsFragmentToolbar?.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

    }

    private fun setupObservers() {
        vm.getUserInfo(userLogin!!).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource){
                    is Resource.Success -> {
                        Log.e("Loading", "Status success")
                        if(binding != null) {
                            with(binding!!) {
                                detailsProgressBar.visibility = View.GONE
                                infoLayout.visibility = View.VISIBLE
                                retryFragmentDetailsButton.visibility = View.GONE
                                retryFragmentDetailsTextView.visibility = View.GONE
                                if (resource.data != null) {
                                    val user: DomainUserInfoModel = resource.data
                                    if (user.avatar != null) {
                                        Glide.with(root.context).load(user.avatar)
                                            .placeholder(R.drawable.github_logo)
                                            .into(avatarDetailsImageView)
                                    } else {
                                        avatarDetailsImageView.setImageResource(R.drawable.github_logo)
                                    }
                                    userDataBinding = user
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        Log.e("Loading", "Status error")
                        if(binding != null) {
                            with(binding!!) {
                                detailsProgressBar.visibility = View.GONE
                                nameDetails.text = userLogin
                                avatarDetailsImageView.setImageResource(R.drawable.github_logo)
                                infoLayout.visibility = View.GONE
                                retryFragmentDetailsButton.visibility = View.VISIBLE
                                retryFragmentDetailsTextView.visibility = View.VISIBLE
                            }
                        }
                    }
                    is Resource.Loading -> {
                        Log.e("Loading", "Status loading")
                        if(binding != null) {
                            with(binding!!) {
                                detailsProgressBar.visibility = View.VISIBLE
                                nameDetails.text = userLogin
                                infoLayout.visibility = View.GONE
                                retryFragmentDetailsButton.visibility = View.GONE
                                retryFragmentDetailsTextView.visibility = View.GONE
                            }
                        }
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}