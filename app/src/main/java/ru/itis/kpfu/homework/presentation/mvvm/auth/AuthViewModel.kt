package ru.itis.kpfu.homework.presentation.mvvm.auth

import androidx.lifecycle.ViewModel
import ru.itis.kpfu.homework.domain.auth.LoginUseCase
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    @ArgCityId cityId: Int,
    loginUseCase: LoginUseCase
) : ViewModel() {

}
