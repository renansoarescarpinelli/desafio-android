package com.picpay.desafio.android.repository

import com.picpay.desafio.android.data.PicPayService
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.util.RequestHandler
import com.picpay.desafio.android.util.call

class PicPayRepositoryImpl(
    private val service: PicPayService
) : PicPayRepository {
    override suspend fun getUsers(): RequestHandler<List<User>> {
        return call { service.getUsers() }
    }
}