package com.dobler.bro.di

import com.dobler.bro.api.BroService
import com.dobler.bro.interactor.GetUsersInteractor
import com.dobler.bro.ui.login.LoginViewModel
import com.dobler.bro.ui.main.MainViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object AppModule {

    val apiModule = module {
        single {
            initRetrofit()
        }
        single {
            get<Retrofit>().create(BroService::class.java) as BroService
        }
    }

    val interactorModule = module {
        single { GetUsersInteractor(get()) }
    }

    val vieModelModule = module {
        viewModel { LoginViewModel(get()) }
        viewModel { MainViewModel(get()) }
    }

    private fun initRetrofit(): Retrofit {
        val httpBuilder = OkHttpClient.Builder()

        httpBuilder.readTimeout(15, TimeUnit.SECONDS)
        httpBuilder.connectTimeout(15, TimeUnit.SECONDS)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://5d160d72df4e5f00145caa2b.mockapi.io/")
            .client(httpBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())

        return retrofit.build()
    }
}
