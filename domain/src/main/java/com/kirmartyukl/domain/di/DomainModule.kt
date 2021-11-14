package com.kirmartyukl.domain.di

import com.kirmartyukl.domain.usecase.GetJokesUseCase
import com.kirmartyukl.domain.usecase.GetJokesUseCaseImpl
import com.kirmartyukl.domain.usecase.RxGetJokesUseCase
import com.kirmartyukl.domain.usecase.RxGetJokesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun providesGetJokesUseCase(jokesUseCase: GetJokesUseCaseImpl): GetJokesUseCase {
        return jokesUseCase
    }

    @Provides
    fun providesRxGetJokesUseCase(rxJokesUseCase: RxGetJokesUseCaseImpl): RxGetJokesUseCase {
        return rxJokesUseCase
    }
}