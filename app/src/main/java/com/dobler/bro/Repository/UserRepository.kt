package com.dobler.bro.Repository

import com.dobler.bro.api.BroService

class UserRepository(var service: BroService) {

    suspend fun getUser(username: String?, password: String?) = service.getUser(username, password)

}
