package ru.itis.kpfu.homework.domain.auth

class LoginUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        username: String,
        password: String,
    ) = authRepository.login(username, password)

}
