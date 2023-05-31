package ru.itis.kpfu.homework.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import ru.itis.kpfu.homework.utils.ResourceProvider
import ru.itis.kpfu.homework.utils.ResourceProviderImpl

@Module
class AppModule {

    @Provides
    fun provideResourceProvider(
        context: Context
    ): ResourceProvider = ResourceProviderImpl(context)

    @Provides
    fun provideFusedLocationClient(
        context: Context
    ): FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

}
