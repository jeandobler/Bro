package com.dobler.bro.interactor

import com.dobler.bro.api.BroService
import com.dobler.bro.vo.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateUserInteractor(private val service: BroService) {

    suspend fun execute(user: User, bro: Int?) =
        withContext(Dispatchers.IO) {
            service.updateUser(user.id, bro)
        }

}
