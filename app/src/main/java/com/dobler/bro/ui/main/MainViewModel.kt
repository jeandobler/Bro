package com.dobler.bro.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dobler.bro.interactor.GetUsersInteractor
import com.dobler.bro.vo.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(private val interactor: GetUsersInteractor) : ViewModel() {

    val users = MutableLiveData<ResponseState<List<User>>>()

    fun fetchUsers() {
        GlobalScope.launch {
            try {
                users.postValue(Loading())
                val response = interactor.execute(null, null)

                if (response.isNotEmpty()) {
                    users.postValue(Success(response))
                    Log.e("MainViewModel", "Success")
                } else {
                    Log.e("MainViewModel", "not found")
                    users.postValue(Error("Users not Found"))
                }

            } catch (e: Exception) {
                users.postValue(Error("Without Connection"))
            }
        }
    }
}
