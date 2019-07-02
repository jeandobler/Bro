package com.dobler.bro

import android.app.Application
import com.dobler.bro.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(
                listOf(
//                    AppModule.rxModule,
                    AppModule.vieModelModule,
                    AppModule.repositoriesModule,
                    AppModule.apiModule
//                    AppModule.repositoriesModule
                )
            )
        }
    }
}