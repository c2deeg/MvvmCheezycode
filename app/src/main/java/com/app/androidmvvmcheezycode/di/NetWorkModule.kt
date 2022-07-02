package com.app.androidmvvmcheezycode.di

import com.app.androidmvvmcheezycode.Utils.Constants.BASE_URL
import com.app.androidmvvmcheezycode.api.UserAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetWorkModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder().
        addConverterFactory(GsonConverterFactory.
        create()).baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun providesUSerAPI(retrofit: Retrofit):UserAPI{
        return retrofit.create(UserAPI::class.java)
    }
}