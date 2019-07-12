package com.dobler.bro.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dobler.bro.interactor.GetUsersInteractor
import com.dobler.bro.vo.*
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(private val interactor: GetUsersInteractor) : ViewModel() {

    private val usersEvent = LiveEvent<ResponseState<List<User>>>()
    val users: LiveData<ResponseState<List<User>>> = usersEvent

    fun fetchUsers() {
        GlobalScope.launch {
            try {

                usersEvent.postValue(Loading())
                val response = interactor.execute(null, null)

                if (response.isNotEmpty()) {
                    usersEvent.postValue(Success(response))
                } else {
                    usersEvent.postValue(Error("Users not Found"))
                }

            } catch (e: Exception) {
                usersEvent.postValue(Error("Without Connection"))
            }
        }
    }
}
