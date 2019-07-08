package com.dobler.bro.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dobler.bro.interactor.GetUsersInteractor
import com.dobler.bro.vo.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(private val interactor: GetUsersInteractor) : ViewModel() {


    val users = MutableLiveData<ResponseState<List<User>>>()

    fun fetchUsersByUsernameAndPassword(username: String, password: String) {
        GlobalScope.launch {
            try {
                users.postValue(Loading())
                val response = interactor.execute(username, password)
                if (response.isNotEmpty()) {
                    users.postValue(Success(response))
                }
                users.postValue(Error("User not Found"))
            } catch (e: Exception) {
                users.postValue(Error("Without Connection"))
            }

        }
    }
}