package com.dobler.bro.di

import com.dobler.bro.BuildConfig
import com.dobler.bro.api.BroService
import com.dobler.bro.interactor.GetUsersInteractor
import com.dobler.bro.interactor.UpdateUserInteractor
import com.dobler.bro.ui.contact.ContactViewModel
import com.dobler.bro.ui.login.LoginViewModel
import com.dobler.bro.ui.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object AppModule {

    val apiModule = module {
        single {
            initHttp()
        }
        single {
            get<Retrofit>().create(BroService::class.java) as BroService
        }
    }

    val networkModule = module {

        single<RetrofitService> {
            RetrofitServiceImplement(
                get(),
                "https://5d160d72df4e5f00145caa2b.mockapi.io/"
            )
        }
    }

    val interactorModule = module {
        single { GetUsersInteractor(get()) }
        single { UpdateUserInteractor(get()) }
    }

    val vieModelModule = module {
        viewModel { LoginViewModel(get()) }
        viewModel { MainViewModel(get()) }
        viewModel { ContactViewModel(get()) }
    }

    private fun initHttp(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()

        httpBuilder.readTimeout(15, TimeUnit.SECONDS)
        httpBuilder.connectTimeout(15, TimeUnit.SECONDS)

        val client =
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder().addInterceptor(interceptor).build()
            } else {
                httpBuilder.build()
            }

        return client
    }
 
}
