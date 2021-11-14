package com.martuk.testchuck

import android.app.Application
import com.kirmartyukl.data.di.DataModule
import com.kirmartyukl.domain.di.DomainModule
import dagger.Module
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}