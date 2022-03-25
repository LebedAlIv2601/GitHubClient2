package com.example.githubclient2.presentation.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubclient2.R
import com.example.githubclient2.databinding.FragmentMainBinding
import com.example.githubclient2.domain.model.DomainUserModel
import com.example.githubclient2.presentation.details.fragment.DetailsFragment
import com.example.githubclient2.presentation.main.recyclerview.LoaderStateAdapter
import com.example.githubclient2.presentation.main.recyclerview.PagingUserAdapter
import com.example.githubclient2.presentation.main.vm.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private val vm: MainViewModel by viewModel()

    private var binding: FragmentMainBinding? = null

    private var pagingAdapter: PagingUserAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_main, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupRecyclerView()
        setupListeners()

    }

    private fun setupObservers() {
        vm.userListData.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                pagingAdapter?.submitData(it)
            }
        })
    }

    private fun setupListeners() {
        binding?.apply {

            retryFragmentButton.setOnClickListener {
                pagingAdapter?.retry()
            }

            swipeRefresh.setOnRefreshListener { pagingAdapter?.refresh() }

            pagingAdapter?.addLoadStateListener {
                swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                usersRecyclerView.isVisible = it.refresh !is LoadState.Loading
                userFragmentProgressBar.isVisible = it.refresh is LoadState.Loading
                retryFragmentButton.isVisible = it.refresh is LoadState.Error
                retryFragmentTextView.isVisible = it.refresh is LoadState.Error
            }
        }
    }

    private fun onUserClick(user: DomainUserModel) {
        val fragmentDetails = DetailsFragment()

        fragmentDetails.arguments =  bundleOf("bundleUserKey" to user.login)
        parentFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragmentDetails)
            .hide(this@MainFragment)
            .addToBackStack(null)
            .commit()
    }


    private fun setupRecyclerView(){
        pagingAdapter = PagingUserAdapter{onUserClick(it)}

        binding?.apply {

            usersRecyclerView.adapter = pagingAdapter
                ?.withLoadStateHeaderAndFooter(
                    header = LoaderStateAdapter { pagingAdapter?.retry() },
                    footer = LoaderStateAdapter { pagingAdapter?.retry() })
            usersRecyclerView.layoutManager = LinearLayoutManager(this@MainFragment.context)
            usersRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    usersRecyclerView.context,
                    DividerItemDecoration.VERTICAL
                )
            )

        }
    }

    override fun onDestroyView() {
        binding = null
        pagingAdapter = null
        super.onDestroyView()
    }
}