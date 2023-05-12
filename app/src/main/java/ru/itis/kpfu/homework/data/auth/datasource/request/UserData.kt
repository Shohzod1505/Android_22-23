package ru.itis.kpfu.homework.data.auth.datasource.request

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("phone")
    val name: String,
    @SerializedName("password")
    val password: String,
)
