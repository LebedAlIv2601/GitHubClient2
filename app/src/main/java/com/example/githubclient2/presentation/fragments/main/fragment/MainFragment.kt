package com.example.githubclient2.presentation.fragments.main.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubclient2.R
import com.example.githubclient2.databinding.FragmentMainBinding
import com.example.githubclient2.domain.model.DomainUserModel
import com.example.githubclient2.presentation.fragments.main.recyclerview.UserAdapter
import com.example.githubclient2.presentation.fragments.main.vm.MainViewModel
import com.example.githubclient2.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private val vm: MainViewModel by viewModel<MainViewModel>()

    private lateinit var adapter: UserAdapter
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_main, container, false)

        setupUI()
        setupObservers()

        return binding.root
    }

    private fun setupUI(){
        adapter = UserAdapter(mutableListOf())
        binding.usersRecyclerView.adapter = adapter
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.usersRecyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.usersRecyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun setupObservers(){
        vm.getUserList().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status){
                    Status.SUCCESS -> {
                        Log.e("Loading", "Status success")
                        binding.usersRecyclerView.visibility = View.VISIBLE
                        binding.userListProgressBar.visibility = View.GONE
                        resource.data?.let{users -> retrieveList(users)}
                    }
                    Status.ERROR -> {
                        Log.e("Loading", "Status error")
                        binding.usersRecyclerView.visibility = View.VISIBLE
                        binding.userListProgressBar.visibility = View.GONE
                        it.message?.let { it1 -> Log.e("Loading", it1) }
                        Toast.makeText(this.context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Log.e("Loading", "Status loading")
                        binding.userListProgressBar.visibility = View.VISIBLE
                        binding.usersRecyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: MutableList<DomainUserModel>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }

}