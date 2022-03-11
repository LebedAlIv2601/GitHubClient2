package com.example.githubclient2.presentation.fragments.main.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubclient2.R
import com.example.githubclient2.databinding.FragmentMainBinding
import com.example.githubclient2.presentation.fragments.main.recyclerview.LoaderStateAdapter
import com.example.githubclient2.presentation.fragments.main.recyclerview.PagingUserAdapter
import com.example.githubclient2.presentation.fragments.main.vm.MainViewModel
import com.example.githubclient2.utils.Status
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private val vm: MainViewModel by viewModel<MainViewModel>()

    //private lateinit var adapter: UserAdapter
    private lateinit var binding: FragmentMainBinding
//    private lateinit var loaderStateAdapter: LoaderStateAdapter

    private val pagingAdapter by lazy(LazyThreadSafetyMode.NONE) {
        PagingUserAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_main, container, false)

        setupUI()



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.retryFragmentButton.setOnClickListener {
            pagingAdapter.retry()
        }
//        setupObservers()
    }


    private fun setupUI(){
//        val pagingAdapter = PagingUserAdapter()
        binding.apply {
            usersRecyclerView.adapter = pagingAdapter
                .withLoadStateHeaderAndFooter(
                    header = LoaderStateAdapter{pagingAdapter.retry()},
                    footer = LoaderStateAdapter{pagingAdapter.retry()})
            usersRecyclerView.layoutManager = LinearLayoutManager(this@MainFragment.context)
            usersRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    usersRecyclerView.context,
                    DividerItemDecoration.VERTICAL
                )
            )

            swipeRefresh.setOnRefreshListener { pagingAdapter.refresh() }

            vm.getUserList().observe(viewLifecycleOwner, Observer{
                lifecycleScope.launch{
                    pagingAdapter.submitData(it)
                }
            })

            pagingAdapter.addLoadStateListener {
                swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                usersRecyclerView.isVisible = it.refresh !is LoadState.Loading
                userFragmentProgressBar.isVisible = it.refresh is LoadState.Loading
                retryFragmentButton.isVisible = it.refresh is LoadState.Error
                retryFragmentTextView.isVisible = it.refresh is LoadState.Error
            }

        }
    }

//    private fun setupObservers(){
//        vm.getUserList().observe(viewLifecycleOwner, Observer {
//            it?.let { resource ->
//                when (resource.status){
//                    Status.SUCCESS -> {
//                        Log.e("Loading", "Status success")
//                        with(binding) {
//                            usersRecyclerView.visibility = View.VISIBLE
//                            userFragmentProgressBar.visibility = View.GONE
//                            retryFragmentButton.visibility = View.GONE
//                            retryFragmentTextView.visibility = View.GONE
//                        }
////                        resource.data?.let{
////                            lifecycleScope.launch{
////                                it.value?.let { it1 -> pagingAdapter.submitData(it1) }
////                            }
////                        }
//                        resource.data?.value?.let {
//                            lifecycleScope.launch{
//                                pagingAdapter.submitData(it)
//                            }
//                        }
//                    }
//                    Status.ERROR -> {
//                        Log.e("Loading", "Status error")
//                        with(binding) {
//                            usersRecyclerView.visibility = View.VISIBLE
//                            userFragmentProgressBar.visibility = View.GONE
//                            retryFragmentButton.visibility = View.VISIBLE
//                            retryFragmentTextView.text = it.message
//                            retryFragmentTextView.visibility = View.VISIBLE
//                        }
////                        it.message?.let { it1 -> Log.e("Loading", it1) }
////                        Toast.makeText(this.context, it.message, Toast.LENGTH_LONG).show()
//                    }
//                    Status.LOADING -> {
//                        Log.e("Loading", "Status loading")
//                        with(binding) {
//                            usersRecyclerView.visibility = View.VISIBLE
//                            userFragmentProgressBar.visibility = View.VISIBLE
//                            retryFragmentButton.visibility = View.GONE
//                            retryFragmentTextView.visibility = View.GONE
//                        }
//                    }
//                }
//            }
//        })
//    }

//    private fun retrieveList(users: MutableList<DomainUserModel>) {
//        adapter.apply {
//            addUsers(users)
//            notifyDataSetChanged()
//        }
//    }

}