package com.dobler.bro.vo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class User(
    val id: Int,
    val name: String,
    val avatar: String,
    val email: String,
    val Password: String?,
    val cpf: String,
    val createdAt: Date,
    val phone: String?,

    val bro: Int
) : Parcelable
