package ru.itis.kpfu.homework.data.auth.datasource

import retrofit2.http.Body
import retrofit2.http.POST
import ru.itis.kpfu.homework.data.auth.datasource.request.UserData

interface AuthApi {

    @POST
    suspend fun login(@Body userData: UserData)

}
