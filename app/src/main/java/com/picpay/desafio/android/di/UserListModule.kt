package com.picpay.desafio.android.di

import com.picpay.desafio.android.view.userlist.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object UserListModule {

    val instance = module {
        viewModel { UserListViewModel(get()) }
    }
}