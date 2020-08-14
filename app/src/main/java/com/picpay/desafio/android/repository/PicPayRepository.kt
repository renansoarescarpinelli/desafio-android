package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.util.RequestHandler

interface PicPayRepository {
    suspend fun getUsers(): RequestHandler<List<User>>
}