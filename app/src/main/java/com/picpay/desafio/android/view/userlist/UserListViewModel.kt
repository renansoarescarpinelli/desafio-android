package com.picpay.desafio.android.view.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.PicPayService
import com.picpay.desafio.android.model.User
import kotlinx.coroutines.launch

class UserListViewModel(private val service: PicPayService) : ViewModel() {

    private val _liveData = MutableLiveData<List<User>>()
    val liveData: LiveData<List<User>>
        get() = _liveData

    fun getUsers() {
        viewModelScope.launch {
            _liveData.value = service.getUsers()
        }
    }
}