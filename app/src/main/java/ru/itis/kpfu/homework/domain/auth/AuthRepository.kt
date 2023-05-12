package ru.itis.kpfu.homework.domain.auth

interface AuthRepository {

    suspend fun login(username: String, password: String)

    suspend fun signOut()

}
