package com.dobler.bro.ui.login

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.liveData
import com.dobler.bro.Repository.UserRepository
import kotlinx.coroutines.Dispatchers

class LoginViewModel(val repository: UserRepository) : ViewModel() {

    val users = liveData(Dispatchers.IO) {
        val retrivedTodo = repository.getUser(null, null)

        emit(retrivedTodo)
    }

}
