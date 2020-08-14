package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.ServiceModule
import com.picpay.desafio.android.di.UserListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PicPayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PicPayApplication)
            modules(listOf(ServiceModule.instance, UserListModule.instance))
        }
    }
}