package com.dobler.bro.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dobler.bro.databinding.MainFragmentBinding
import com.dobler.bro.vo.Error
import com.dobler.bro.vo.Loading
import com.dobler.bro.vo.Success
import com.dobler.bro.vo.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private lateinit var mainListAdapter: MainListAdapter
    private lateinit var binding: MainFragmentBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(layoutInflater)

        (activity as AppCompatActivity).setSupportActionBar(binding.mainToolbar)

        loadData()

        userListener()
        refreshListListener()

        return binding.root
    }

    private fun refreshListListener() {
        binding.srLoadingList.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        viewModel.fetchUsers()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainListAdapter = MainListAdapter {
            onUserClick(it)
        }

        binding.rvUsersList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainListAdapter
        }
    }

    private fun onUserClick(user: User) {
        val action = MainFragmentDirections.actionMainFragmentToContactFragment(user)
        findNavController().navigate(action)
    }


    private fun userListener() {
        viewModel.users.observe(this, androidx.lifecycle.Observer { response ->

            when (response) {
                is Success -> {
                    mainListAdapter.setData(response.data)
                    binding.pbLoading.visibility = View.GONE
                    binding.srLoadingList.isRefreshing = false
                }
                is Error -> {
                    mainListAdapter.clearAdapter()
                    binding.pbLoading.visibility = View.GONE
                    binding.tvError.visibility = View.VISIBLE
                    binding.srLoadingList.isRefreshing = false
                }
                is Loading -> {
                    binding.tvError.visibility = View.GONE
                    binding.pbLoading.visibility = View.VISIBLE
                }
            }
        })

    }
}
