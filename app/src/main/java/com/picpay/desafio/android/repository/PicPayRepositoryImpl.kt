package com.picpay.desafio.android.repository

import com.picpay.desafio.android.data.PicPayService
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.util.RequestHandler
import com.picpay.desafio.android.util.call
import kotlinx.coroutines.Dispatchers

class PicPayRepositoryImpl(
    private val service: PicPayService
) : PicPayRepository {
    override suspend fun getUsers(): RequestHandler<List<User>> {
        return call(Dispatchers.IO) { service.getUsers() }
    }
}