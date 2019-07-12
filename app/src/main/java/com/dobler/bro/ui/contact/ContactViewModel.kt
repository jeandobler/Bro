package com.dobler.bro.ui.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dobler.bro.interactor.UpdateUserInteractor
import com.dobler.bro.vo.*
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class ContactViewModel(val interactor: UpdateUserInteractor) : ViewModel() {

    private val usersEvent = LiveEvent<ResponseState<User>>()
    val user: LiveData<ResponseState<User>> = usersEvent

    var currentUser: User? = null

    fun updateBroCount(user: User, newBroValue : Int) {
        GlobalScope.launch {
            try {
                usersEvent.postValue(Loading())
                val response = interactor.execute(user, newBroValue)

                usersEvent.postValue(Success(response))

            } catch (e: Exception) {
                Timber.e(e)
                usersEvent.postValue(Error("Error on send Bro"))
            }

        }

    }

}
