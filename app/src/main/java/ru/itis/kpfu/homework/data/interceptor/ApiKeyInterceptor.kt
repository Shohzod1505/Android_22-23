package ru.itis.kpfu.homework.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.itis.kpfu.homework.BuildConfig

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val newURL = original.url.newBuilder()
            .addQueryParameter("appid", BuildConfig.API_KEY)
            .build()

        return chain.proceed(
            original.newBuilder()
                .url(newURL)
                .build()
        )
    }
}
