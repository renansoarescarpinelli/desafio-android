package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.PicPayService
import com.picpay.desafio.android.repository.PicPayRepository
import com.picpay.desafio.android.repository.PicPayRepositoryImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val url = "http://careers.picpay.com/tests/mobdev/"

object ServiceModule {

    val instance = module {
        single<OkHttpClient> { OkHttpClient.Builder().build() }

        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl(url)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        single<PicPayService> { get<Retrofit>().create(PicPayService::class.java) }

        single<PicPayRepository> { PicPayRepositoryImpl(get()) }
    }
}