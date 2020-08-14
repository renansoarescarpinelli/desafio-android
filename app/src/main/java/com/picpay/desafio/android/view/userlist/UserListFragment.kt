package com.picpay.desafio.android.view.userlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import kotlinx.android.synthetic.main.user_list_fragment.*
import org.koin.android.ext.android.inject

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
        viewModel.liveData.observe(this, Observer {
            user_list_progress_bar.visibility = View.GONE
            userListAdapter.users = it
        })
    }
}