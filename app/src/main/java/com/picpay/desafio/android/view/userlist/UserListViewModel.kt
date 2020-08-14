package com.picpay.desafio.android.view.userlist

import androidx.lifecycle.*
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.PicPayRepository
import com.picpay.desafio.android.util.RequestHandler
import kotlinx.coroutines.launch

class UserListViewModel(private val repository: PicPayRepository) : ViewModel() {

    private val _userList = MutableLiveData<RequestHandler<List<User>>>()
    val userList: LiveData<RequestHandler<List<User>>>
        get() = _userList

    fun getUsers() {
        viewModelScope.launch {
            _userList.value = repository.getUsers()
        }
    }
}