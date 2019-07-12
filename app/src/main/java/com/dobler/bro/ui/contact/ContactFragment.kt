package com.dobler.bro.ui.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dobler.bro.databinding.ContactFragmentBinding
import com.dobler.bro.vo.Success
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ContactFragment : Fragment() {

    private lateinit var binding: ContactFragmentBinding
    private val viewModel: ContactViewModel  by viewModel()

    val args: ContactFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ContactFragmentBinding.inflate(inflater)
        viewModel.currentBro = if (viewModel.currentBro == null) args.user.bro else viewModel.currentBro
        binding.obj = args.user

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayShowHomeEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        Glide.with(this)
            .load(args.user.avatar)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(RequestOptions.circleCropTransform())
            .into(binding.ivAvatar)

        onSendBroClick()
        onSendObservable()

        return binding.root
    }

    private fun onSendObservable() {
        viewModel.user.observe(this, Observer { response ->

            when (response) {
                is Success -> {
                    binding.btSendBro.visibility = View.VISIBLE
                    binding.obj = response.data
                    viewModel.currentBro  = response.data.bro
                }
                else -> {
                    Timber.e("Error on send bro")
                }
            }
        })
    }

    private fun onSendBroClick() {
        binding.btSendBro.setOnClickListener {
            viewModel.updateBroCount(args.user)
            binding.btSendBro.visibility = View.INVISIBLE
        }
    }

}
