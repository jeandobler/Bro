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
//        val view = inflater.inflate(R.layout.login_fragment, container, false)

        (activity as AppCompatActivity).supportActionBar!!.hide()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)


        binding.btLogin.setOnClickListener {
            binding.pbLogin.visibility = View.VISIBLE
            binding.tvError.text = "Wrong credentials!"
            binding.tvError.visibility = View.VISIBLE
            binding.btLogin.visibility = View.INVISIBLE

            viewModel.users.observe(this, Observer {
                Log.e("LoginFragment", it[1].avatar)
            })

//            Thread.sleep(1000)
//            binding.pbLogin.visibility = View.GONE
//            binding.btLogin.visibility = View.VISIBLE
        }

    }


}
