package com.educorreia.chefssecrets.core.ui

import android.app.Application
import com.educorreia.chefssecrets.core.data.di.apiModule
import com.educorreia.chefssecrets.core.ui.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            androidLogger()

            modules(appModule, apiModule)
        }
    }
}