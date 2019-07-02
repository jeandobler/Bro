package com.dobler.bro.vo

import java.util.*


data class User(
    val id: Int,
    val name: String,
    val createdAt: Date,
    val avatar: String,
    val email: String,
    val cpf: String,
    val bro: Int
)