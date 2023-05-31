package ru.itis.kpfu.homework.domain.auth

import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        username: String,
        password: String,
    ) = authRepository.login(username, password)

}
