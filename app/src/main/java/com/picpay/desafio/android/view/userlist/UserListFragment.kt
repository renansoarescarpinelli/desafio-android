package com.picpay.desafio.android.view.userlist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.util.RequestHandler.*
import kotlinx.android.synthetic.main.user_list_fragment.*
import org.koin.android.ext.android.inject

private val NETWORK_ERROR = "Sem conexão"

class UserListFragment : Fragment(R.layout.user_list_fragment) {

    private val viewModel: UserListViewModel by inject()
//    private val navController: NavController by lazy { requireView().findNavController() }

    private val userListAdapter =
        UserListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = userListAdapter
        }
        user_list_progress_bar.visibility = View.VISIBLE
        setObservable()
        viewModel.getUsers()
    }

    private fun setObservable() {
        viewModel.userList.observe(this, Observer {
            when (it) {
                is Success -> handleSuccess(it.value)
                is NetworkError -> handleNetworkError()
                is GenericError -> handleError(it.throwable)
            }
        })
    }

    private fun handleSuccess(listUser: List<User>) {
        userListAdapter.users = listUser
        hideLoading()
    }

    private fun handleNetworkError() {
        hideLoading()
        recyclerView.visibility = View.GONE
        hideLoading()
        Toast.makeText(context, NETWORK_ERROR, Toast.LENGTH_SHORT).show()
    }

    private fun handleError(throwable: Throwable) {
        hideLoading()
        recyclerView.visibility = View.GONE
        Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun hideLoading() {
        user_list_progress_bar.visibility = View.GONE
    }
}