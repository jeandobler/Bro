package com.dobler.bro.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dobler.bro.databinding.LoginFragmentBinding
import com.dobler.bro.vo.Error
import com.dobler.bro.vo.Loading
import com.dobler.bro.vo.Success
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var binding: LoginFragmentBinding

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(layoutInflater)

        (activity as AppCompatActivity).supportActionBar!!.hide()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btLogin.setOnClickListener {
            viewModel.fetchUsersByUsernameAndPassword(
                binding.etLogin.text.toString(),
                binding.etPassword.text.toString()
            )
        }

        loginAction()

    }

    private fun loginAction() {
        viewModel.users.observe(this, Observer { response ->

            when (response) {

                is Success -> {
                    binding.pbLogin.visibility = View.INVISIBLE
                    binding.btLogin.visibility = View.VISIBLE

                    binding.tvError.apply {
                        visibility = View.INVISIBLE
                        text = ""
                    }
                    Log.e("LoginFragment", "Success")
                }
                is Error -> {
                    binding.tvError.apply {
                        visibility = View.VISIBLE
                        text = "Wrong credentials!"
                    }

                    binding.pbLogin.visibility = View.INVISIBLE
                    binding.btLogin.visibility = View.VISIBLE

                    Log.e("LoginFragment", "Error")
                }
                is Loading -> {
                    binding.pbLogin.visibility = View.VISIBLE
                    binding.btLogin.visibility = View.INVISIBLE
                    Log.e("LoginFragment", "Loading")
                }
            }

        })

    }

}
