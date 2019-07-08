package com.dobler.bro.interactor

import com.dobler.bro.api.BroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUsersInteractor(private val service: BroService) {

    suspend fun execute(username: String?, password: String?) =
        withContext(Dispatchers.IO) {
            service.getUser(username, password)
        }

}
