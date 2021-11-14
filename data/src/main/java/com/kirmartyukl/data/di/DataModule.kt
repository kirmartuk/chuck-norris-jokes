package com.kirmartyukl.data.di

import com.kirmartyukl.data.api.JokesApiService
import com.kirmartyukl.data.api.JokesRepositoryImpl
import com.kirmartyukl.data.rx.RxJokesRepositoryImpl
import com.kirmartyukl.domain.repository.JokesRepository
import com.kirmartyukl.domain.repository.RxJokesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    private const val BASE_URL = "http://api.icndb.com/"

    @Singleton
    @Provides
    fun provideCallAdapter(): RxJava3CallAdapterFactory =
        RxJava3CallAdapterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(rxJava3CallAdapterFactory: RxJava3CallAdapterFactory): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .baseUrl(BASE_URL)
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): JokesApiService =
        retrofit.create(JokesApiService::class.java)

    @Singleton
    @Provides
    fun providesRepository(jokesRepositoryImpl: JokesRepositoryImpl)
            : JokesRepository =
        jokesRepositoryImpl

    @Singleton
    @Provides
    fun providesRxRepository(rxJokesRepositoryImpl: RxJokesRepositoryImpl): RxJokesRepository =
        rxJokesRepositoryImpl
}