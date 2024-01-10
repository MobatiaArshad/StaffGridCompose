package com.mobileapp.staffgridcompose.di

import com.google.gson.GsonBuilder
import com.mobileapp.staffgridcompose.BuildConfig
import com.mobileapp.staffgridcompose.api.HeaderInterceptor
import com.mobileapp.staffgridcompose.api.StaffXApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StaffXApiModule {

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder()
        .readTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(logging)
        .build()

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(client)
            .build()


    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): StaffXApi = retrofit.create(StaffXApi::class.java)
}