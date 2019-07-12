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
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_fragment.*
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
        viewModel.currentUser = if (viewModel.currentUser == null ) args.user  else viewModel.currentUser
        binding.obj = viewModel.currentUser


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
                    viewModel.currentUser = response.data


                    Snackbar.make(binding.root, "Bro Added", Snackbar.LENGTH_SHORT)
                        .show()

                }
                else -> {
                    Timber.e("Error on send bro")
                }
            }
        })
    }

    private fun onSendBroClick() {
        binding.btSendBro.setOnClickListener {
            viewModel.currentUser?.bro?.plus(1)?.let { it1 -> viewModel.updateBroCount(args.user, it1) }
            binding.btSendBro.visibility = View.INVISIBLE
        }
    }

}
