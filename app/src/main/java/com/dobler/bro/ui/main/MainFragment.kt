package com.dobler.bro.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

        (activity as AppCompatActivity).supportActionBar!!.hide()

        return binding.root
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

        mainListStart()

    }

    private fun onUserClick(user: User) {
        Log.e("MainFragment", "userCLicked")
    }


    private fun mainListStart() {
        viewModel.users.observe(this, androidx.lifecycle.Observer { response ->

            when (response) {

                is Success -> {
                    mainListAdapter.setData(response.data)
                }
                is Error -> {
                    mainListAdapter.clearAdapter()
                }
                is Loading -> {
                }
            }

        })

        viewModel.fetchUsers()

    }


}
