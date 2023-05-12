package ru.itis.kpfu.homework.data.auth

import ru.itis.kpfu.homework.data.auth.datasource.AuthApi
import ru.itis.kpfu.homework.data.auth.datasource.request.UserData
import ru.itis.kpfu.homework.domain.auth.AuthRepository

class AuthRepositoryImpl(
    private val authApi: AuthApi
): AuthRepository {

    override suspend fun login(username: String, password: String) {
        authApi.login(
            UserData(
            name = username,
            password = password,
        )
        )
    }

    override suspend fun signOut() {
        authApi
    }
}
